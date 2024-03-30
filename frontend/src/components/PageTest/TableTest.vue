<template>
    <div id="app">
        <!-- Filters -->
        <div class="filter">
            <label for="search">Search:</label>
            <input type="text" id="search" name="search" v-model="searchTerm" />
        </div>

        <!-- Customization options -->
        <div class="customization">
            <label for="rowHeight">Row Height:</label>
            <select
                id="rowHeight"
                name="rowHeight"
                v-model="selectedRowHeight"
                @change="applyRowHeight()">
                <option value="condensed">Condensed</option>
                <option value="regular">Regular</option>
                <option value="relaxed">Relaxed</option>
            </select>
        </div>

        <table>
            <thead class="fixed-header">
                <tr>
                    <th @click="sortColumn(0)">
                        회사명
                        <span
                            v-if="sortColumnIndex === 0"
                            :class="{
                                'arrow-up': sortDirection === 'asc',
                                'arrow-down': sortDirection === 'desc',
                            }"></span>
                    </th>
                    <th @click="sortColumn(1)">
                        업종
                        <span
                            v-if="sortColumnIndex === 1"
                            :class="{
                                'arrow-up': sortDirection === 'asc',
                                'arrow-down': sortDirection === 'desc',
                            }"></span>
                    </th>
                    <th @click="sortColumn(2)">
                        주소
                        <span
                            v-if="sortColumnIndex === 2"
                            :class="{
                                'arrow-up': sortDirection === 'asc',
                                'arrow-down': sortDirection === 'desc',
                            }"></span>
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(company, index) in displayedCompanies" :key="index">
                    <td>{{ company.companyname }}</td>
                    <td>{{ company.companytype }}</td>
                    <td>{{ company.region }}</td>
                </tr>
            </tbody>
        </table>

        <!-- Pagination -->
        <div class="pagination">
            <button
                v-for="pageNumber in totalPages"
                :key="pageNumber"
                @click="goToPage(pageNumber)"
                :class="{ active: currentPage === pageNumber }">
                {{ pageNumber }}
            </button>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';

const companyList = ref([
    { companyname: '이지소프트', companytype: '제조업', region: '서울', companyId: 1 },
    { companyname: '(주)넥스틴', companytype: '금융업', region: '부산', companyId: 2 },
    {
        companyname: '주식회사 클라우드시스템',
        companytype: '서비스업',
        region: '대구',
        companyId: 3,
    },
    { companyname: '넥스엔정보기술(주)', companytype: 'IT업', region: '인천', companyId: 4 },
    { companyname: '부곡스텐레스(주)', companytype: '제조업', region: '광주', companyId: 5 },
    { companyname: '단암시스템즈 주식회사', companytype: '금융업', region: '대전', companyId: 6 },
    { companyname: '파크시스템스(주)', companytype: '서비스업', region: '울산', companyId: 7 },
    { companyname: '라온시큐어(주)', companytype: 'IT업', region: '세종', companyId: 8 },
    { companyname: '(주)플래티어', companytype: '제조업', region: '경기', companyId: 9 },
    { companyname: '한국무라타전자(주)', companytype: '금융업', region: '강원', companyId: 10 },

    // More sample data...
]);

// Pagination
const currentPage = ref(1);
const itemsPerPage = 5; // 한 페이지당 표시할 항목 수
const totalPages = computed(() => Math.ceil(companyList.value.length / itemsPerPage));

function goToPage(pageNumber) {
    currentPage.value = pageNumber;
}

// Sorting
const sortColumnIndex = ref(0); // 초기값을 첫 번째 열(회사명)의 인덱스로 설정
const sortDirection = ref('desc'); // 디폴트로 내림차순으로 설정

function sortColumn(index) {
    if (sortColumnIndex.value === index) {
        sortDirection.value = sortDirection.value === 'asc' ? 'desc' : 'asc';
    } else {
        sortColumnIndex.value = index;
        sortDirection.value = 'asc';
    }

    companyList.value.sort((a, b) => {
        const fieldA = a[Object.keys(a)[index]].toLowerCase();
        const fieldB = b[Object.keys(b)[index]].toLowerCase();
        if (sortDirection.value === 'asc') {
            return fieldA.localeCompare(fieldB);
        } else {
            return fieldB.localeCompare(fieldA);
        }
    });
}

// Filtering
const searchTerm = ref('');
const filteredCompanies = computed(() => {
    const lowercaseSearchTerm = searchTerm.value.toLowerCase();
    return companyList.value.filter(
        (company) =>
            company.companyname.toLowerCase().includes(lowercaseSearchTerm) ||
            company.companytype.toLowerCase().includes(lowercaseSearchTerm) ||
            company.region.toLowerCase().includes(lowercaseSearchTerm)
    );
});

// Displayed companies based on pagination
const displayedCompanies = computed(() => {
    const startIndex = (currentPage.value - 1) * itemsPerPage;
    return filteredCompanies.value.slice(startIndex, startIndex + itemsPerPage);
});

// Customization options
const selectedRowHeight = ref('regular');

function applyRowHeight() {
    const table = document.querySelector('table');
    table.classList.remove('condensed', 'regular', 'relaxed');
    table.classList.add(selectedRowHeight.value);
}
</script>

<style scoped>
/* Styles for the table */
table {
    border-collapse: collapse;
    width: 100%;
}

th,
td {
    padding: 16px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

th {
    background-color: #f2f2f2;
    cursor: pointer; /* 마우스 커서를 포인터로 변경 */
    position: relative;
}

th .arrow-up,
th .arrow-down {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    width: 0;
    height: 0;
    border-left: 4px solid transparent;
    border-right: 4px solid transparent;
}

th .arrow-up {
    border-bottom: 4px solid black; /* 오름차순 화살표 */
}

th .arrow-down {
    border-top: 4px solid black; /* 내림차순 화살표 */
}

tr:nth-child(even) {
    background-color: #f2f2f2;
}

/* Different row heights */
.condensed {
    line-height: 40px;
}

.regular {
    line-height: 48px;
}

.relaxed {
    line-height: 56px;
}

/* Monospaced font for numeric data */
.monospaced {
    font-family: 'Courier New', Courier, monospace;
}

/* Fixed header when scrolling */
.fixed-header th {
    position: sticky;
    top: 0;
    background-color: #f2f2f2;
}

/* Hover action for rows */
tbody tr:hover {
    background-color: #ddd;
}

/* Pagination styles */
.pagination {
    margin-top: 20px;
}

.pagination button {
    background-color: #f2f2f2;
    border: none;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
    cursor: pointer;
}

.pagination button.active {
    background-color: #ddd;
}

/* Filters styles */
.filter {
    margin-bottom: 20px;
}

.filter input[type='text'] {
    padding: 8px;
    width: 200px;
}

.filter select {
    padding: 8px;
}

/* Customization options styles */
.customization {
    margin-bottom: 20px;
}

.customization label {
    margin-right: 10px;
}
</style>
