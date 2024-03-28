<template>
    <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner" style="height: 300px">
            <div class="carousel-item active" data-bs-interval="300">
                <img src="@/assets/img/test1.jpg" class="d-block w-100" alt="Image 1" />
            </div>
            <div class="carousel-item" data-bs-interval="300">
                <img src="@/assets/img/test2.jpg" class="d-block w-100" alt="Image 2" />
            </div>
            <div class="carousel-item" data-bs-interval="300">
                <img src="@/assets/img/test1.jpg" class="d-block w-100" alt="Image 3" />
            </div>
        </div>
        <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleInterval"
            data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleInterval"
            data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</template>

<script setup></script>

<style scoped></style>
