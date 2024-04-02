<template>
    <h1
        style="
            margin-bottom: 5rem;
            font-family: 'Pretendard-Light';
            text-align: start;
            margin-left: 1em;
            margin-bottom: 40px;
            margin-top: 80px;
        ">
        # 조회수 많은 기업
    </h1>
    <section style="background-color: rgb(241, 241, 241); height: 50vh" class="d-flex">
        <div class="container" style="align-items: center; justify-content: center">
            <Swiper
                :loop="true"
                :speed="15000"
                :free-mode="true"
                :modules="[EffectCards, Autoplay]"
                :autoplay="{
                    delay: 1,
                    disableOnInteraction: false,
                }">
                <SwiperSlide>
                    <div class="swiper-slide">
                        <div class="card-container" v-for="comapny in companyList1">
                            <div class="card card-size">
                                <div class="card-body">
                                    <img
                                    class="img-size"
                                        src="@/assets/img/jobis.png"
                                        alt="" />
                                    <h5 class="card-title font">
                                        {{ comapny.name }}
                                    </h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </SwiperSlide>
                <SwiperSlide>
                    <div class="swiper-slide">
                        <div class="card-container" v-for="company in companyList2">
                            <div class="card card-size">
                                <div class="card-body">
                                    <img
                                        class="img-size"
                                        src="@/assets/img/jobis.png"
                                        alt="" />
                                    <h5 class="card-title font">
                                        {{ company.name }}
                                    </h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </SwiperSlide>
            </Swiper>
        </div>
    </section>
</template>

<script setup>
import { Swiper, SwiperSlide } from 'swiper/vue';
import { EffectCards, Autoplay } from 'swiper/modules';
import 'swiper/css';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import { onMounted, ref } from 'vue';

const store = useCounterStore();
const companyList = ref(null);
const companyList1 = ref([])
const companyList2 = ref([])

onMounted(() => {
    getTopViewList();
});
const getTopViewList = () => {
    axios({
        method: 'get',
        url: `${store.API_URL}/api/company/popular`,
    })
        .then((res) => {
            companyList.value = res.data.data;
            console.log('상위 조회수 리스트 조회 : ', companyList.value);
            // companyList를 반으로 나누어 companyList1과 companyList2에 할당
            const halfIndex = Math.ceil(companyList.value.length / 2);
            companyList1.value = companyList.value.slice(0, halfIndex);
            companyList2.value = companyList.value.slice(halfIndex);
         
        })
        .catch((err) => {
            console.log(err);
        });
};


</script>

<style scoped>
.swiper {
    width: 100%;
    height: 80%;
    margin-left: 1.5rem;
}
.swiper-slide {
    text-align: center;

    background: rgb(241, 241, 241);
    display: flex;
    justify-content: center;
    align-items: center;
    transition: transform 0.3s;
}
.card {
    border: none;
}

.card-container {
    width: 20rem;
    height: 10rem;
}
.card-size {
    width: 10rem;
    height: 10rem;
}
.img-size {
    height: 7rem;
    width: 7rem;
    margin-bottom: 0.5rem;
}
.font {
    font-family: 'Pretendard-Light';
    font-size: 0.8rem;
}
</style>
