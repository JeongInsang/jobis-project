<template>
    <div class="container">
        <h2>기업 리스트</h2>
        <!-- <SearchButton /> -->
        <div class="d-flex w-100">
            <div class="input-group mb-3 ms-3 w-25">
                <input
                    type="text"
                    class="form-control"
                    placeholder="회사명을 검색하세요"
                    aria-label="Recipient's username"
                    aria-describedby="button-addon2" />
                <button class="btn btn-outline-secondary" type="button" id="button-addon2">
                    <i class="bi bi-search"></i>
                </button>
            </div>
        </div>
        <div class="d-flex">
            <button class="button m-1" @click="clickType">산업군</button>
            <button class="button m-1">지역</button>
        </div>
        <div v-if="typeIsclick" class="d-flex" v-bind="typeIsclick">
            <button v-for="industry in industries" class="button m-1">{{ industry }}</button>
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
                                <td>{{ company.companyname }}</td>
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
import SearchButton from '@/components/PageTest/SearchButton.vue';
import { ref, computed } from 'vue';

const pageSize = 10; // 한 페이지당 보여질 데이터 개수
const currentPage = ref(0); // 현재 페이지
const dummyData = [
    { companyname: '회사명 1', companytype: '제조업', region: '서울' },
    { companyname: '회사명 2', companytype: '금융업', region: '부산' },
    { companyname: '회사명 3', companytype: '서비스업', region: '대구' },
    { companyname: '회사명 4', companytype: 'IT업', region: '인천' },
    { companyname: '회사명 5', companytype: '제조업', region: '광주' },
    { companyname: '회사명 6', companytype: '금융업', region: '대전' },
    { companyname: '회사명 7', companytype: '서비스업', region: '울산' },
    { companyname: '회사명 8', companytype: 'IT업', region: '세종' },
    { companyname: '회사명 9', companytype: '제조업', region: '경기' },
    { companyname: '회사명 10', companytype: '금융업', region: '강원' },
    { companyname: '회사명 11', companytype: '서비스업', region: '충청' },
    { companyname: '회사명 12', companytype: 'IT업', region: '전라' },
    { companyname: '회사명 13', companytype: '제조업', region: '경상' },
    { companyname: '회사명 14', companytype: '금융업', region: '제주' },
    { companyname: '회사명 15', companytype: '서비스업', region: '전남' },
    { companyname: '회사명 16', companytype: 'IT업', region: '전북' },
    { companyname: '회사명 17', companytype: '제조업', region: '충남' },
    { companyname: '회사명 18', companytype: '금융업', region: '경북' },
    { companyname: '회사명 19', companytype: '서비스업', region: '경남' },
    { companyname: '회사명 20', companytype: 'IT업', region: '강서' },
    { companyname: '회사명 21', companytype: 'IT업', region: '강서' },
];

// 필터 검색 버튼 리스트
const industries = [
    '건설',
    '광업',
    '교육',
    '금융/보험',
    '농/임/어업',
    '도/소매',
    '보건/사회복지',
    '부동산',
    '임대업',
    '원료 재생',
    '숙박/음식점',
    '예술/스포츠',
    '운수/창고',
    '전기/가스/증기',
    '과학기술',
    '정보통신',
    '제조',
    '기타',
];

// 페이지네이션 계산을 위한 computed property
const paginatedData = computed(() => {
    const startIndex = currentPage.value * pageSize;
    const endIndex = startIndex + pageSize;
    return dummyData.slice(startIndex, endIndex);
});

// 총 페이지 수 계산
const totalPages = Math.ceil(dummyData.length / pageSize);

// 이전 페이지로 이동하는 함수
const prevPage = () => {
    if (currentPage.value > 0) {
        currentPage.value--;
    }
};

// 다음 페이지로 이동하는 함수
const nextPage = () => {
    if (currentPage.value < totalPages - 1) {
        currentPage.value++;
    }
};

// 특정 페이지로 이동하는 함수
const changePage = (page) => {
    currentPage.value = page;
};

// 산업군 별로 필터 로직
const typeIsclick = ref(false);
const clickType = () => {
    console.log('클릭 함수 실행');
    typeIsclick.value = true;
};
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
</style>
