FROM openjdk:17-jdk
CMD ["./gradlew", "clean", "build"]
ARG JAR_FILE=build/libs/*.jar
# 애플리케이션의 JAR 파일을 컨테이너에 app.jar라는 이름으로 복사
# 실제 build된 jar 파일 경로와 build.gradle을 참고하여 파일 이름을 작성해 주어야 한다
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]