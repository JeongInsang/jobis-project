<template>
    <div class="carousel-wrapper">
        <div data-position="top" class="carousel">
            <span class="carousel__text"
                >• card component • card component • card component • card component • card
                component • card component</span
            >
        </div>
        <div data-direction="right" data-position="bottom" class="carousel">
            <span class="carousel__text"
                >• card component • card component • card component • card component • card
                component • card component</span
            >
        </div>
    </div>
</template>

<script setup></script>

<style scoped>
.carousel-wrapper {
    position: relative;
}

.carousel {
    position: absolute;
    left: 0;
    width: 200%; /* 요소가 화면의 두 배로 넓어지도록 설정 */

    animation: carousel 10s linear var(--carousel_direction, normal) infinite;
}

.carousel[data-direction='right'] {
    --carousel_direction: reverse;
}

.carousel[data-position='top'] {
    top: 0.75rem;
}

.carousel[data-position='bottom'] {
    bottom: 0.75rem;
}

.carousel .carousel__text {
    font-size: 0.75rem;
    color: black;
    text-wrap: nowrap;
}

@keyframes carousel {
    0%,
    100% {
        transform: translate(0%);
    }
    50% {
        transform: translate(-50%);
    }
}
</style>
