<template>
    <div style="font-family: 'Pretendard-Light'" class="container">
        <h2 style="font-family: 'Pretendard-Bold'">기업 리스트</h2>
        <!-- <SearchButton /> -->
        <div class="d-flex w-100">
            <div class="input-group mb-3 ms-3 w-25">
                <input
                    @keydown.enter="search"
                    v-bind="keyword"
                    type="text"
                    class="form-control"
                    placeholder="회사명을 검색하세요"
                    aria-label="Recipient's username"
                    aria-describedby="button-addon2" />
                <button
                    @click="search"
                    class="btn btn-outline-secondary"
                    type="button"
                    id="button-addon2">
                    <i class="bi bi-search"></i>
                </button>
            </div>
        </div>
        <div class="d-flex">
            <button class="button m-1" :class="{ selected: regionIsclick }" @click="clickRiegon">
                지역
            </button>
            <button class="button m-1" :class="{ selected: typeIsclick }" @click="clickType">
                산업군
            </button>
        </div>
        <!-- 지역 버튼 클릭 시 나오는 항목들 -->
        <div v-if="regionIsclick" class="d-flex col-9" v-bind="regionIsclick">
            <button
                @click="toggleRegionButton(region.id)"
                v-for="region in regions"
                :key="region.id"
                :class="{ buttonS: true, selected: isRegionSelected(region.id) }"
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
                :class="{ buttonS: true, selected: isTypeSelected(industry.typeId) }"
                class="buttonS m-1 w-100">
                {{ industry.industry }}
            </button>
        </div>
        <div v-if="typeIsclick" class="d-flex col-9" v-bind="typeIsclick">
            <button
                @click="toggleButton(industry.typeId)"
                v-for="industry in industries2"
                :key="industry.typeId"
                :class="{ buttonS: true, selected: isTypeSelected(industry.typeId) }"
                class="buttonS m-1 w-100">
                {{ industry.industry }}
            </button>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <table class="table table-bordered table-hover dt-responsive">
                        <caption class="text-center">
                            캡션 자리
                        </caption>
                        <thead>
                            <tr>
                                <th>회사명</th>
                                <th>산업군</th>
                                <th>지역</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(company, index) in paginatedData" :key="index">
                                <td @click="goCompanyDeatil(company.companyId)" class="cursor">
                                    {{ company.companyname }}
                                </td>
                                <td>{{ company.companytype }}</td>
                                <td>{{ company.region }}</td>
                            </tr>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <li class="page-item" :class="{ disabled: currentPage === 0 }">
                                <a
                                    class="page-link"
                                    href="#"
                                    aria-label="Previous"
                                    @click="prevPage">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li
                                class="page-item"
                                v-for="page in totalPages"
                                :key="page"
                                :class="{ active: page === currentPage + 1 }">
                                <a class="page-link" href="#" @click="changePage(page - 1)">{{
                                    page
                                }}</a>
                            </li>
                            <li
                                class="page-item"
                                :class="{ disabled: currentPage === totalPages - 1 }">
                                <a class="page-link" href="#" aria-label="Next" @click="nextPage">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import router from '@/router';
import { ref, computed, onMounted, watch } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
const store = useCounterStore();
const API_URL = store.API_URL;

const page = ref(1);
const size = ref(null);
const keyword = ref('');
const type = ref('');
const location = ref('');
const comapnyDataList = ref([]);
onMounted(() => {
    // getCompanyList();
    search();
});

// 회사명 검색 로직
const search = function () {
    axios({
        method: 'get',
        url: `${API_URL}/api/company/search?page=${page.value}&size=${size.value}&keyword=${keyword.value}&location=${location.value}&type=${type.value}`,
    })
        .then((res) => {
            comapnyDataList.value = res.data;
            console.log(comapnyDataList.value);
        })
        .catch((err) => {
            console.error('회사명 검색 실패:', err);
        });
};

// 산업군 필터 검색 버튼 리스트
const industries1 = [
    { typeId: 1, industry: '건설' },
    { typeId: 2, industry: '광업' },
    { typeId: 3, industry: '교육' },
    { typeId: 4, industry: '금융/보험' },
    { typeId: 5, industry: '농/임/어업' },
    { typeId: 6, industry: '도/소매' },
    { typeId: 7, industry: '보건/사회복지' },
    { typeId: 8, industry: '부동산' },
    { typeId: 9, industry: '임대업' },
];

const industries2 = [
    { typeId: 10, industry: '원료 재생' },
    { typeId: 11, industry: '숙박/음식점' },
    { typeId: 12, industry: '예술/스포츠' },
    { typeId: 13, industry: '운수/창고' },
    { typeId: 14, industry: '전기/가스/증기' },
    { typeId: 15, industry: '과학기술' },
    { typeId: 16, industry: '정보통신' },
    { typeId: 17, industry: '제조' },
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

// watch로 현재 페이지 값이 바뀔 때 마다 회사리스트 가져오는 함수 실행
// watch(currentPage.value, getCompanyList);

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
        if (selectTypeFilter.value[i] === buttonId) {
            selectTypeFilter.value.splice(i, 1);
            isCheckedType.value = true;
            break;
        }
    }
    if (isCheckedType.value === false) {
        selectTypeFilter.value.push(buttonId);
    }
    isCheckedType.value = false;
    console.log('선택된 산업군 필터 항목들 : ', selectTypeFilter.value);
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
    console.log('선택된 지역 필터 항목들 : ', selectRegionFilter.value);
};

// 산업군 버튼 토글
const isTypeSelected = (buttonId) => {
    return selectTypeFilter.value.includes(buttonId);
};

// 지역 버튼 토글
const isRegionSelected = (buttonId) => {
    return selectRegionFilter.value.includes(buttonId);
};

// Filter가 선택 될 때 마다 필터된 요청 보내기
watch(
    [selectRegionFilter, selectTypeFilter],
    ([newRegionFilters, newTypeFilters], [oldRegionFilters, oldTypeFilters]) => {
        axios({
            method: 'get', // GET 또는 POST에 따라 요청 방식을 변경
            url: `${API_URL}/company/search?keyword=${keyword.value}&location=${newRegionFilters}&type=${newTypeFilters}`,
        })
            .then((response) => {
                // 응답 받은 데이터를 저장
                comapnyDataList.value = res.data;
                console.log('필터링된 회사 리스트:', comapnyDataList.value);
            })
            .catch((error) => {
                // 오류 처리
                console.error('필터링된 회사를 가져오는 중 오류 발생:', error);
            });
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
body {
    font-size: 140%;
}

h2 {
    text-align: center;
    padding: 20px 0;
}

table caption {
    padding: 0.5em 0;
}

table.dataTable th,
table.dataTable td {
    white-space: nowrap;
}

.p {
    text-align: center;
    padding-top: 140px;
    font-size: 14px;
}

/* 버튼 css */
.button {
    width: 5em;
    height: 3em;
    border-radius: 30em;
    font-size: 15px;
    font-family: inherit;
    border: none;
    position: relative;
    overflow: hidden;
    z-index: 1;
    box-shadow:
        3px 3px 6px #c5c5c5,
        -3px -3px 6px #ffffff;
}

.button::before {
    content: '';
    width: 0;
    height: 3em;
    border-radius: 30em;
    position: absolute;
    top: 0;
    left: 0;
    background-image: linear-gradient(to right, #0fd850 0%, #f9f047 100%);
    transition: 0.5s ease;
    display: block;
    z-index: -1;
}

.button:hover::before {
    width: 9em;
}

/* 작은 버튼 */
.buttonS {
    width: 5em;
    height: 3em;
    border-radius: 30em;
    font-size: 12px;
    font-family: inherit;
    border: none;
    position: relative;
    overflow: hidden;
    z-index: 1;
    box-shadow:
        3px 3px 6px #c5c5c5,
        -3px -3px 6px #ffffff;
}

.buttonS::before {
    content: '';
    width: 0;
    height: 3em;
    border-radius: 30em;
    position: absolute;
    top: 0;
    left: 0;
    background-image: linear-gradient(to right, #0fd850 0%, #f9f047 100%);
    transition: 0.5s ease;
    display: block;
    z-index: -1;
}

.buttonS:hover::before {
    width: 9em;
}

.selected {
    /* 선택된 버튼의 스타일 */
    background-color: greenyellow;
}
/* 마우스 커서 모양 */
.cursor {
    cursor: pointer;
}
</style>
