<template>
    <div class="table-reservation" @dragover.prevent="handleDragOver" ref="tableReservation">
        <h3>가게구조 및 테이블 설정</h3>
        <!-- <div class="buttons">
            <button @click="addTable(2)" class="newbutton">2인 테이블</button>
            <button @click="addTable(4)" class="newbutton">4인 테이블</button>
            <button @click="addTable(1)" class="newbutton">1인 자리</button>
            <button @click="addRoundTable()" class="newbutton">원형 테이블</button>
            <button @click="addCounter()" class="newbutton">카운터</button>
            <button @click="addHorizontalWindow()" class="newbutton">창문</button>
            <button @click="addVerticalWindow()" class="newbutton">창문</button>
            <button @click="addEntrance()" class="newbutton">입구</button>
            <button @click="addRoom()" class="newbutton">룸</button>
            <button @click="addGroupTable()" class="newbutton">단체석</button>
        </div> -->
        <div class="text-end">
            <v-menu open-on-hover>
                <template v-slot:activator="{ props }">
                    <v-btn color="primary" v-bind="props"> 테이블 선택하기 </v-btn>
                </template>

                <v-list>
                    <!-- <v-list-item v-for="(item, index) in items" :key="index">
                        <v-list-item-title class="newbutton">{{ item.title }}</v-list-item-title>
                    </v-list-item> -->
                    <v-list-item>
                        <button @click="addTable(2)">2인 테이블</button>
                    </v-list-item>
                    <v-list-item>
                        <button @click="addTable(4)">4인 테이블</button>
                    </v-list-item>
                    <v-list-item>
                        <button @click="addTable(1)">1인 자리</button>
                    </v-list-item>
                    <v-list-item>
                        <button @click="addRoundTable()">원형 테이블</button>
                    </v-list-item>
                    <v-list-item>
                        <button @click="addCounter()">카운터</button>
                    </v-list-item>
                    <v-list-item>
                        <button @click="addHorizontalWindow()">창문(가로)</button>
                    </v-list-item>
                    <v-list-item>
                        <button @click="addVerticalWindow()">창문(세로)</button>
                    </v-list-item>
                    <v-list-item>
                        <button @click="addEntrance()">입구</button>
                    </v-list-item>
                    <v-list-item>
                        <button @click="addRoom()">룸</button>
                    </v-list-item>
                    <v-list-item>
                        <button @click="addGroupTable()">단체석</button>
                    </v-list-item>
                </v-list>
            </v-menu>
            <button
                @click="openModal"
                style="
                    padding: 10px;
                    margin-top: 10px;
                    margin-left: 30px;
                    text-decoration-line: underline;
                ">
                저장하기
            </button>
        </div>
        <hr />
        <div
            v-for="table in tables"
            :key="table.diningTableNumber"
            class="table"
            :style="{
                top: table.diningTableY + 'px',
                left: table.diningTableX + 'px',
                width: table.width + 'px',
                height: table.height + 'px',
                borderRadius: table.diningTableType === '원형테이블' ? '50%' : '7%',
                backgroundColor:
                    table.diningTableType === '창문'
                        ? 'skyblue'
                        : table.diningTableType === '카운터'
                          ? '#FFD28F'
                          : table.diningTableType === '입구'
                            ? '#A3C9AA'
                            : table.diningTableType === '룸' || table.diningTableType === '단체석'
                              ? '#ffcccb'
                              : '#C69774',
                boxShadow: '0px 5px 10px rgba(0, 0, 0, 0.2)',
            }"
            @mousedown="startDragging(table, $event)"
            @touchstart="startDragging(table, $event)"
            @touchend="stopDragging(table, $event)"
            @dblclick="removeTable(table)">
            <span v-if="table.diningTableType === '창문'">
                <!-- Condition for window type -->
                <span v-if="table.width > table.height">창문</span>
                <span v-else-if="table.width < table.height">창문</span>
            </span>
            <span v-else-if="table.diningTableType === '카운터'">
                <!-- Condition for counter type -->
                카운터
            </span>
            <span v-else-if="table.diningTableType === '입구'">
                <!-- Condition for entrance type -->
                입구
            </span>
            <span v-else-if="table.diningTableType === '룸' || table.diningTableType === '단체석'">
                <!-- Condition for room and group table type -->
                {{ table.diningTableType }}
            </span>
            <span v-else>
                <!-- Default text for other table types -->
                {{ table.diningTableCapacity }}인
            </span>
        </div>
        <v-dialog v-model="modal" max-width="400">
            <v-card v-if="tables.length == 0">
                <v-card-title>테이블 설정 저장</v-card-title>
                <v-card-text> 테이블 설정을 저장하시겠습니까? </v-card-text>
                <v-card-actions>
                    <v-btn @click="closeModal">취소</v-btn>
                    <v-btn @click="saveTableDataAndCloseModal">확인</v-btn>
                </v-card-actions>
            </v-card>
            <v-card v-else>
                <v-card-title>테이블 설정 수정</v-card-title>
                <v-card-text> 테이블 설정을 수정하시겠습니까? </v-card-text>
                <v-card-actions>
                    <v-btn @click="closeModal">취소</v-btn>
                    <v-btn @click="saveTableDataAndCloseModal">확인</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import router from '@/router';
import { computed } from 'vue';

const store = useCounterStore();
const tables = ref([]);
const accessToken = localStorage.getItem('accessToken'); // Replace with your actual access token
const uniqueTableId = ref(0);

onMounted(() => {
    getTableData();
});

const getTableData = function () {
    axios({
        method: 'get',
        url: `${store.API_URL}/api/store/${store.storeId}/dining-tables`,
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            console.log(res.data);
            tables.value = res.data;
            console.log(tables.value);
            console.log(tables.value.length);
            if (tables.value.length > 0) {
                uniqueTableId.value = tables.value[tables.value.length - 1].diningTableNumber;
                console.log('TableID', uniqueTableId.value);
            }
        })
        .catch((err) => {
            console.log(err);
        });
};

const saveTableData = function () {
    axios({
        method: 'post',
        url: `${store.API_URL}/api/store/${store.storeId}/dining-tables`,
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
        data: tables.value, // 배열로 직접 tables를 보냄
    })
        .then((res) => {
            console.log(res.data);
            alert('저장이 완료되었습니다');
            router.push({ name: 'OwnerReservationView' });
        })
        .catch((err) => {
            console.log(tables.value);
            console.error(err);
        });
};
const updateTableData = function () {
    axios({
        method: 'put',
        url: `${store.API_URL}/api/store/${store.storeId}/dining-tables`,
        headers: {
            Authorization: `Bearer ${accessToken}`,
            // 'Content-Type': 'application/json', // 이 줄을 추가하여 콘텐츠 유형을 명시
        },
        data: tables.value, // 배열로 직접 tables를 보냄
    })
        .then((res) => {
            console.log(res.data);
            router.push({ name: 'OwnerReservationView' });
        })
        .catch((err) => {
            console.log(tables.value);
            console.error(err);
        });
};

const addTable = (capacity) => {
    let width, height;
    if (capacity === 2) {
        width = height = 60;
    } else if (capacity === 4) {
        width = height = 80;
    } else if (capacity === 1) {
        width = height = 40;
    }
    uniqueTableId.value += 1;
    const newTable = {
        diningTableNumber: uniqueTableId.value,
        diningTableCapacity: capacity,
        diningTableType: `${capacity}인 테이블`,
        diningTableX: 0,
        diningTableY: 150,
        width: width,
        height: height,
        diningTableIsAvailable: true,
    };
    tables.value.push(newTable);
};

const addRoundTable = () => {
    uniqueTableId.value += 1;
    const newTable = {
        diningTableNumber: uniqueTableId.value,
        diningTableCapacity: 4,
        diningTableType: '원형테이블',
        diningTableX: 0,
        diningTableY: 150,
        width: 80,
        height: 80,
        diningTableIsAvailable: true,
    };
    tables.value.push(newTable);
};

const addCounter = () => {
    uniqueTableId.value += 1;
    const newTable = {
        diningTableNumber: uniqueTableId.value,
        diningTableCapacity: 0,
        diningTableType: '카운터',
        diningTableX: 0,
        diningTableY: 150,
        width: 60,
        height: 60,
        diningTableIsAvailable: false,
    };
    tables.value.push(newTable);
};

const addHorizontalWindow = () => {
    uniqueTableId.value += 1;
    const newTable = {
        diningTableNumber: uniqueTableId.value,
        diningTableCapacity: 0,
        diningTableType: '창문',
        diningTableX: 0,
        diningTableY: 150,
        width: 80,
        height: 30,
        diningTableIsAvailable: false,
    };
    tables.value.push(newTable);
};

const addVerticalWindow = () => {
    uniqueTableId.value += 1;
    const newTable = {
        diningTableNumber: uniqueTableId.value,
        diningTableCapacity: 0,
        diningTableType: '창문',
        diningTableX: 0,
        diningTableY: 150,
        width: 30,
        height: 80,
        diningTableIsAvailable: false,
    };
    tables.value.push(newTable);
};

const addEntrance = () => {
    uniqueTableId.value += 1;
    const newTable = {
        diningTableNumber: uniqueTableId.value,
        diningTableCapacity: 0,
        diningTableType: '입구',
        diningTableX: 0,
        diningTableY: 150,
        width: 60,
        height: 30,
        diningTableIsAvailable: false,
    };
    tables.value.push(newTable);
};

const addRoom = () => {
    uniqueTableId.value += 1;
    const newTable = {
        diningTableNumber: uniqueTableId.value,
        diningTableCapacity: 4,
        diningTableType: '룸',
        diningTableX: 0,
        diningTableY: 150,
        width: 60,
        height: 60,
        diningTableIsAvailable: true,
    };
    tables.value.push(newTable);
};

const addGroupTable = () => {
    uniqueTableId.value += 1;
    const newTable = {
        diningTableNumber: uniqueTableId.value,
        diningTableCapacity: 8,
        diningTableType: '단체석',
        diningTableX: 0,
        diningTableY: 150,
        width: 160,
        height: 80,
        diningTableIsAvailable: true,
    };
    tables.value.push(newTable);
};

const removeTable = (table) => {
    const index = tables.value.findIndex((t) => t.diningTableNumber === table.diningTableNumber);
    if (index !== -1) {
        tables.value.splice(index, 1);
    }
};

const startDragging = (table, event) => {
    event.preventDefault();
    const offsetX = event.clientX - table.diningTableX;
    const offsetY = event.clientY - table.diningTableY;

    const moveHandler = (e) => {
        const newX = e.clientX - offsetX;
        const newY = e.clientY - offsetY;

        // 음수로 이동하는 경우에는 업데이트를 취소
        if (newX < 0 || newY < 0) {
            return;
        }

        table.diningTableX = newX;
        table.diningTableY = newY;
    };

    const endHandler = () => {
        document.removeEventListener('mousemove', moveHandler);
        document.removeEventListener('mouseup', endHandler);
        document.removeEventListener('touchmove', moveHandler);
        document.removeEventListener('touchend', endHandler);
    };

    document.addEventListener('mousemove', moveHandler);
    document.addEventListener('mouseup', endHandler);
    document.addEventListener('touchmove', moveHandler, { passive: false });
    document.addEventListener('touchend', endHandler);
};

const handleDragOver = function (event) {
    const { offsetX, offsetY } = event.nativeEvent;
    const tableReservationEl = refs.tableReservation;

    const isInsideRestrictedArea =
        offsetX >= 0 &&
        offsetY >= 0 &&
        offsetX <= tableReservationEl.clientWidth &&
        offsetY <= tableReservationEl.clientHeight;

    if (!isInsideRestrictedArea) {
        event.preventDefault();
        tableReservationEl.classList.add('restricted-drag');
    } else {
        tableReservationEl.classList.remove('restricted-drag');
    }
};
const modal = ref(false);

const openModal = function () {
    modal.value = true;
};

const closeModal = function () {
    modal.value = false;
};

const saveTableDataAndCloseModal = function () {
    saveTableData(); // 변경된 정보를 서버에 저장
    closeModal(); // 모달 닫기
};
</script>

<style scoped>
.calendar,
.time-selection,
.table-reservation {
    padding: 10px; /* Add padding inside each component */
    box-sizing: border-box; /* Include padding and border in element's total width and height */
}

.table-reservation {
    position: relative;
    background-color: #ffffff;
    width: 90%;
    height: 568px;
    border-radius: 15px;
    box-shadow: 15px 15px 30px #bebebe;
    font-family: 'SejonghospitalBold';
    h3 {
        text-align: center;
        margin-bottom: 10px;
        font-family: 'SejonghospitalBold';
    }

    .table {
        position: absolute;
        color: #fff;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;
        user-select: none;
        font-size: small;
        &:hover {
            background-color: #5f0202;
        }
        /* margin-top: 110px; */
    }
}
/* 
.newbutton {
    font-size: medium;
    padding: 5px;
    border: 3px solid seashell;
    border-radius: 20px;
    background-color: rgb(184, 220, 251);
} */
.buttons {
    padding: 10px;
    padding-left: 20px;
}
.newbutton {
    padding: 15px 25px;
    border: unset;
    color: #212121;
    z-index: 1;
    background: white;
    position: relative;
    font-weight: 1000;
    font-size: 15px;
    -webkit-box-shadow: 4px 8px 19px -3px rgba(0, 0, 0, 0.27);
    box-shadow: 4px 8px 19px -3px rgba(0, 0, 0, 0.27);
    transition: all 250ms;
    overflow: hidden;
    margin-bottom: 10px;
}

.newbutton::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    width: 0;
    background-color: #212121;
    z-index: -1;
    -webkit-box-shadow: 4px 8px 19px -3px rgba(0, 0, 0, 0.27);
    box-shadow: 4px 8px 19px -3px rgba(0, 0, 0, 0.27);
    transition: all 250ms;
}

.newbutton:hover {
    color: #e8e8e8;
}

.newbutton:hover::before {
    width: 100%;
}
</style>
