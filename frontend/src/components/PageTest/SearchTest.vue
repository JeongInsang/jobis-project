<template>
    <div class="container">
        <h2 style="font-family: 'Pretendard-Bold'">기업 리스트</h2>
        <!-- <SearchButton /> -->
        <div class="d-flex w-100">
            <div class="input-group mb-3 ms-3 w-25">
                <input
                    @keydown.enter="fetchData"
                    v-model="keyword"
                    type="text"
                    class="form-control"
                    placeholder="회사명을 검색하세요"
                    aria-label="Recipient's username"
                    aria-describedby="button-addon2" />
                <button
                    @click="fetchData"
                    class="btn btn-outline-secondary"
                    type="button"
                    id="button-addon2">
                    <i class="bi bi-search"></i>
                </button>
            </div>
        </div>
        <div class="d-flex">
            <button class="button m-1" :class="{ active: regionIsclick }" @click="clickRiegon">
                지역
            </button>
            <button class="button m-1" :class="{ active: typeIsclick }" @click="clickType">
                산업군
            </button>
        </div>
        <!-- 지역 버튼 클릭 시 나오는 항목들 -->
        <div v-if="regionIsclick" class="d-flex col-9" v-bind="regionIsclick">
            <button
                @click="toggleRegionButton(region.id)"
                v-for="region in regions"
                :key="region.id"
                :class="{ active: isRegionSelected(region.id) }"
                class="buttonS m-1 w-100">
                {{ region.region }}
            </button>
        </div>
        <!-- 산업군 버튼 클릭 시 나오는 항목들 -->
        <div v-if="typeIsclick" class="d-flex col-9" v-bind="typeIsclick">
            <button
                @click="toggleButton(industry.typeId)"
                v-for="industry in industries1"
                :key="industry.typeId"
                :class="{ active: isTypeSelected(industry.typeId) }"
                class="buttonS m-1 w-100">
                {{ industry.industry }}
            </button>
        </div>
        <div v-if="typeIsclick" class="d-flex col-9" v-bind="typeIsclick">
            <button
                @click="toggleButton(industry.typeId)"
                v-for="industry in industries2"
                :key="industry.typeId"
                :class="{ active: isTypeSelected(industry.typeId) }"
                class="buttonS m-1 w-100">
                {{ industry.industry }}
            </button>
        </div>
    </div>
    <!-- 테이블 임 -->
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <table class="table table-bordered table-hover dt-responsive">
                    <thead>
                        <tr>
                            <th>회사명</th>
                            <th>산업군</th>
                            <th>지역</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(company, index) in data" :key="index">
                            <td @click="goCompanyDeatil(company.companyId)" class="company-detail">
                                {{ company.name }}
                            </td>
                            <td>{{ company.type }}</td>
                            <td>{{ company.address }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- 페이지 네이션 버튼 -->
        <div class="d-flex">
            <button class="container pagenation-btn" @click="previousPage" :disabled="page === 0">
                Previous Page
            </button>
            <button
                class="container pagenation-btn"
                @click="changePage(btn)"
                v-for="(btn, index) in pageButtons"
                :key="index"
                :class="{ active: btn === page }">
                {{ btn + 1 }}
            </button>
            <button class="container pagenation-btn" @click="nextPage" >Next Page</button>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, watch, computed } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import { useRouter } from 'vue-router';

const store = useCounterStore();
const router = useRouter();

// 기업 리스트 받을 변수
const data = ref([]);
const page = ref(store.currentPage);
const size = ref(20); // 페이지당 아이템 수
const keyword = ref('');
const type = ref([]);
const location = ref([]);
const API_URL = store.API_URL;

// 페이지 버튼 목록을 저장할 배열
const pageButtons = ref([]);

const fetchData = async () => {
    try {
        const response = await axios.get(
            `${API_URL}/api/company/search?page=${page.value}&size=${size.value}&keyword=${keyword.value}&location=${location.value}&type=${type.value}`
        );
        data.value = response.data.data.content;
        console.log(data.value)
        
    } catch (error) {
        console.error('Error fetching data:', error);
    }
};

const previousPage = () => {
    if (page.value > 0) {
        page.value--;
        store.currentPage--;
    }
};

const nextPage = () => {
    page.value++;
    store.currentPage++;
};

// 페이지 버튼 클릭 시 해당 페이지로 이동
const changePage = (pageIndex) => {
    page.value = pageIndex;
    store.currentPage = pageIndex;
};

// 데이터의 총 갯수
const totalData = 24800;

// 페이지 버튼 목록 생성
const generatePageButtons = () => {
    const totalPages = Math.ceil(totalData / size.value);
    const maxButtons = 10; // 최대 버튼 수
    const currentPageIndex = page.value;

    let startPage = Math.max(0, currentPageIndex - Math.floor(maxButtons / 2));
    let endPage = Math.min(totalPages - 1, startPage + maxButtons - 1);

    // Adjust startPage and endPage to show exactly maxButtons if possible
    if (endPage - startPage < maxButtons - 1) {
        startPage = Math.max(0, endPage - maxButtons + 1);
    }

    pageButtons.value = Array.from({ length: endPage - startPage + 1 }, (_, i) => startPage + i);
};




onMounted(() => {
    fetchData();
    generatePageButtons();
});

watch(
    () => page.value,
    () => {
        fetchData();
        generatePageButtons();
    }
);

router.beforeEach((to, from, next) => {
    if (to != from) {
        store.currentPage = 0;
        page.value = 0;
    }
    next();
});

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 산업군 필터 검색 버튼 리스트
const industries1 = [
    { typeId: 1, industry: '건설업' },
    { typeId: 2, industry: '광업' },
    { typeId: 3, industry: '교육 서비스업' },
    { typeId: 4, industry: '금융 및 보험업' },
    { typeId: 5, industry: '농업, 임업 및 어업' },
    { typeId: 6, industry: '도매 및 소매업' },
    { typeId: 7, industry: '보건업 및 사회복지 서비스업' },
    { typeId: 8, industry: '부동산업' },
    { typeId: 9, industry: '사업시설 관리, 사업 지원 및 임대 서비스업' },
];

const industries2 = [
    { typeId: 10, industry: '수도, 하수 및 폐기물 처리, 원료 재생업' },
    { typeId: 11, industry: '숙박 및 음식점업' },
    { typeId: 12, industry: '예술, 스포츠 및 여가관련 서비스업' },
    { typeId: 13, industry: '운수 및 창고업' },
    { typeId: 14, industry: '전기, 가스, 증기 및 공기조절 공급업' },
    { typeId: 15, industry: '전문, 과학 및 기술 서비스업' },
    { typeId: 16, industry: '정보통신업' },
    { typeId: 17, industry: '제조업' },
    { typeId: 18, industry: '기타' },
];

// 지역 필터 검색 버튼 리스트
const regions = [
    { id: 1, region: '서울' },
    { id: 2, region: '부산' },
    { id: 3, region: '대구' },
    { id: 4, region: '인천' },
    { id: 5, region: '광주' },
    { id: 6, region: '대전' },
    { id: 7, region: '울산' },
    { id: 8, region: '세종' },
    { id: 9, region: '경기' },
    { id: 10, region: '강원' },
    { id: 11, region: '충북' },
    { id: 12, region: '충남' },
    { id: 13, region: '전남' },
    { id: 14, region: '전북' },
    { id: 15, region: '경남' },
    { id: 16, region: '경북' },
    { id: 17, region: '제주' },
];


// 산업군 별로 필터 로직
const typeIsclick = ref(false);
const clickType = () => {
    if (typeIsclick.value === false) {
        typeIsclick.value = true;
    } else {
        typeIsclick.value = false;
    }
};

// 지역 별로 필터 로직
const regionIsclick = ref(false);
const clickRiegon = () => {
    if (regionIsclick.value === false) {
        regionIsclick.value = true;
    } else {
        regionIsclick.value = false;
    }
};

// 선택된 산업군 필터들
const selectTypeFilter = ref([]);
const isCheckedType = ref(false);

const toggleButton = (buttonId) => {
    for (let i = 0; i < selectTypeFilter.value.length; i++) {
        // selectedTypeFilter 리스트에 i 번째 인덱스에 있는 값이 buttonId(=industries의 typeId 값)과 같으면
        // selectedTypeFilter 리스트에서 해당 값을 제거하여 필터 해제 기능
        if (selectTypeFilter.value[i] === buttonId) {
            selectTypeFilter.value.splice(i, 1);
            console.log(selectTypeFilter.value, 'selectTypeFilter 값')
            isCheckedType.value = true;
            break;
        }
    }
    // 반복문 돌아서 selectTypeFilter 리스트 안에 새로 들어온 buttonId 값이 없으면 리스트에 들어온 값(예: 7,5,3 등등) 추가
    if (isCheckedType.value === false) {
        selectTypeFilter.value.push(buttonId);
        
    }
    isCheckedType.value = false;
    
};

// 선택된 지역 필터들
const selectRegionFilter = ref([]);
const isCheckedRegion = ref(false);

const toggleRegionButton = (buttonId) => {
    
    
    for (let i = 0; i < selectRegionFilter.value.length; i++) {
        if (selectRegionFilter.value[i] === buttonId) {
            selectRegionFilter.value.splice(i, 1);
            
            isCheckedRegion.value = true;
            
            
            break;
        }
    }
    if (isCheckedRegion.value === false) {
        selectRegionFilter.value.push(buttonId);
    }
    isCheckedRegion.value = false;
    
};

// 산업군 토글 버튼이 선택되었다는 css 적용 용도로 만들어 놓음
const isTypeSelected = (buttonId) => {
    return selectTypeFilter.value.includes(buttonId);
};

// 지역 토글 버튼이 선택되었다는 css 적용 용도로 만들어 놓음
const isRegionSelected = (buttonId) => {
    return selectRegionFilter.value.includes(buttonId);
};

// Filter가 선택 될 때 마다 필터된 요청 보내기
watch(
    [selectRegionFilter.value, selectTypeFilter.value],
    ([newRegionFilters, newTypeFilters], [oldRegionFilters, oldTypeFilters]) => {
        console.log('반복문 돌기전 newRegionFilters : ',newRegionFilters)
        // 지역 선택 필터 기능 구현
        location.value = []
        for (let x = 0; x < newRegionFilters.length; x++) {
            let addLocation = ''
            // newRegionFilters 리스트 안에 값들이 Id 값들임 => 4, 9 등등
            // Id 값으로 '대전' 과 같은 str을 매칭 시켜줌
            addLocation = regions[newRegionFilters[x] - 1].region
            // addLocation 은 '대전' => 이러한 값임
            console.log('addLocation : ',addLocation)
            // 이미 location에 담겨져 있지는 않은지 확인 해야 함
            location.value.push(addLocation)
            
        }

        console.log('반복문 돌기전 newTypeFilters : ',newTypeFilters)
        // 산업군 선택 필터 기능 구현
        type.value = []
        for (let i = 0; i < newTypeFilters.length; i++) {
            let addType = ''
            // newTypeFilters 리스트 안에 값들이 Id 값들임 => 4, 9 등등
            // Id 값으로 '제조업' 과 같은 str을 매칭 시켜줌
            if (newTypeFilters[i] < 10) {

                addType = industries1[newTypeFilters[i] - 1].industry
                // addLocation 은 '대전' => 이러한 값임
                console.log('addLocation : ',addType)
            }
            else {
                addType = industries2[newTypeFilters[i] - 10].industry
                // addLocation 은 '대전' => 이러한 값임
                console.log('addLocation : ',addType)
            }
            // 이미 location에 담겨져 있지는 않은지 확인 해야 함
            type.value.push(addType)
            
        }
        
        
        console.log('for 문 다 돌고 location',location.value)
        console.log('for 문 다 돌고 type',type.value)
        
        fetchData()
        
    }
);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 기업 상세 페이지 가기 로직
const goCompanyDeatil = (comapnyId) => [
    router.push({
        name: 'companydetail',
        params: { companyId: comapnyId },
    }),
];
</script>
<style scoped>
.active {
    background-color: #ccc;
}
.pagenation-btn {
    height: 4.2rem;
    width: 4.2rem;
}
.company-detail {
    cursor: pointer;
}
</style>