<template>
    <div class="container px-4 py-5" id="custom-cards">
        <h2 class="pb-2 border-bottom">조회수 상위 기업</h2>

        <div class="row row-cols-1 row-cols-lg-3 align-items-stretch g-4 py-5">
            <div class="col">
                <div
                    class="card card-cover h-100 overflow-hidden text-bg-dark rounded-4 shadow-lg backImg1">
                    <div class="d-flex flex-column h-100 p-5 pb-3 text-white text-shadow-1">
                        <ul class="d-flex list-unstyled mt-auto">
                            <li class="me-auto"><i class="bi bi-gear-fill"></i> 회사명1</li>
                            <li class="d-flex align-items-center me-3">
                                <svg class="bi me-2" width="1em" height="1em">
                                    <use xlink:href="#geo-fill"></use>
                                </svg>
                                <small>제조</small>
                            </li>
                            <li class="d-flex align-items-center">
                                <svg class="bi me-2" width="1em" height="1em">
                                    <use xlink:href="#calendar3"></use>
                                </svg>
                                <small>버튼자리</small>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="col">
                <div
                    class="card card-cover h-100 overflow-hidden text-bg-dark rounded-4 shadow-lg"
                    style="background-image: url('unsplash-photo-2.jpg')">
                    <div class="d-flex flex-column h-100 p-5 pb-3 text-white text-shadow-1">
                        <h3 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold">도/소매업</h3>
                        <ul class="d-flex list-unstyled mt-auto">
                            <li class="me-auto">
                                <i class="bi bi-bag-fill"></i>
                            </li>
                            <li class="d-flex align-items-center me-3">
                                <svg class="bi me-2" width="1em" height="1em">
                                    <use xlink:href="#geo-fill"></use>
                                </svg>
                                <small>도/소매</small>
                            </li>
                            <li class="d-flex align-items-center">
                                <svg class="bi me-2" width="1em" height="1em">
                                    <use xlink:href="#calendar3"></use>
                                </svg>
                                <small>4d</small>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="col">
                <div
                    class="card card-cover h-100 overflow-hidden text-bg-dark rounded-4 shadow-lg"
                    style="background-image: url('unsplash-photo-3.jpg')">
                    <div class="d-flex flex-column h-100 p-5 pb-3 text-shadow-1">
                        <h3 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold">정보통신업</h3>
                        <ul class="d-flex list-unstyled mt-auto">
                            <li class="me-auto">
                                <i class="bi bi-laptop-fill"></i>
                            </li>
                            <li class="d-flex align-items-center me-3">
                                <svg class="bi me-2" width="1em" height="1em">
                                    <use xlink:href="#geo-fill"></use>
                                </svg>
                                <small>정보통신</small>
                            </li>
                            <li class="d-flex align-items-center">
                                <svg class="bi me-2" width="1em" height="1em">
                                    <use xlink:href="#calendar3"></use>
                                </svg>
                                <small>5d</small>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup></script>

<style scoped>
.backImg1 {
    background-image: url('@/img/make.jpg');
}
</style>
