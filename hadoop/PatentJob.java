import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PatentJob {

    public static class PatentMapper extends Mapper<LongWritable, Text, Text, Text> {
        private Map<String, String> companyInfo = new HashMap<>();
        private long startTime;

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
            if (context.getCacheFiles() != null && context.getCacheFiles().length > 0) {
                URI cachedFileUri = context.getCacheFiles()[0];
                Path cachedFilePath = new Path(cachedFileUri);
                FileSystem fs = FileSystem.get(context.getConfiguration());
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(fs.open(cachedFilePath), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",", -1);
                        if (parts.length >= 2) {
                            String companyName = parts[0].trim();
                            String address = parts[1].trim();
                            companyInfo.put(companyName, address);
                        }
                    }
                }
            }
            startTime = System.currentTimeMillis(); // 시작 시간 기록
            context.write(new Text("StartTime"), new Text(String.valueOf(startTime)));
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            try {
                String line = value.toString();
                String[] parts = line.split(","); // CSV 라인을 파싱

                // companyInfo 맵에 저장된 모든 기업명과 주소를 순회
                for (Map.Entry<String, String> entry : companyInfo.entrySet()) {
                    String companyName = entry.getKey();
                    String address = entry.getValue();

                    String[] splitName = parts[1].split("|");

                    boolean companyNameMatched = false;
                    boolean addressMatched = false;

                    for(String name : splitName) {
                        companyNameMatched = name.contains(companyName);
                        addressMatched = name.contains(address);

                        if(companyNameMatched && addressMatched) {
                            break;
                        }
                        companyNameMatched = false;
                        addressMatched = false;
                    }

                    // 두 조건이 모두 참인 경우에만 처리
                    if (companyNameMatched && addressMatched) {
                        context.write(new Text(companyName + "," + address), new Text(parts[0] + "," + parts[1] + "," + parts[2]));
                        long matchTime = System.currentTimeMillis(); // 매칭 성공 시점 기록
                        context.write(new Text("MatchTime"), new Text(String.valueOf(matchTime)));
                        break; // 매칭되는 첫 번째 기업에 대해 처리 후 루프 종료
                    }
                }
            } catch(Exception e) {
                System.err.println("Error processing line: " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    public static class PatentReducer extends Reducer<Text, Text, Text, Text> {
        private long endTime;

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
            endTime = System.currentTimeMillis(); // 종료 시간 기록
        }

        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for (Text value : values) {
                context.write(key, value);
            }
            if (key.toString().equals("EndTime")) {
                context.write(new Text("EndTime"), new Text(String.valueOf(endTime)));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Patent Processing");
        job.setJarByClass(PatentJob.class);
        job.setMapperClass(PatentMapper.class);
        job.setCombinerClass(PatentReducer.class); // 사용하는 경우
        job.setReducerClass(PatentReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // Add company info file to distributed cache
        job.addCacheFile(new URI("/user/hadoop/company2/company_normalize_list.csv"));

        FileInputFormat.addInputPath(job, new Path("/user/hadoop/patent/"));
        FileOutputFormat.setOutputPath(job, new Path(args[0]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
