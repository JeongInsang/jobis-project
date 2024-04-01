<template>
    <h1 class="container" style="margin-bottom: 5rem">#조회수 상위</h1>
    <div class="container">
        <Swiper
            :loop="true"
            :speed="20000"
            :slides-per-view="auto"
            :free-mode="true"
            :modules="[EffectCards, Autoplay]"
            :autoplay="{
                delay: 1,
                disableOnInteraction: false,
            }">
            <SwiperSlide>
                <div class="swiper-slide">
                    <div class="card-container" v-for="comapny in companyList">
                        <div class="card card-size">
                            <div class="card-body">
                                <img
                                    style="height: 7rem; width: 7rem; margin-bottom: 0.5rem"
                                    src="@/assets/img/jobis.png"
                                    alt="" />
                                <h5 class="card-title">{{ comapny }}</h5>
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
                                    style="height: 7rem; width: 7rem; margin-bottom: 0.5rem"
                                    src="@/assets/img/jobis.png"
                                    alt="" />
                                <h5 class="card-title">{{ company }}</h5>
                            </div>
                        </div>
                    </div>
                </div>
            </SwiperSlide>
        </Swiper>
    </div>
</template>

<script setup>
import { Swiper, SwiperSlide } from 'swiper/vue';
import { EffectCards, Autoplay } from 'swiper/modules';
import 'swiper/css';

const companyList = ['상위1', '상위2', '상위3', '상위4', '상위5'];
const companyList2 = ['상위6', '상위7', '상위8', '상위9', '상위10'];
</script>

<style scoped>
.swiper {
    width: 100%;
    height: 80%;
    margin-left: 1.5rem;
}
.swiper-slide {
    text-align: center;

    background: #fff;
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
</style>
