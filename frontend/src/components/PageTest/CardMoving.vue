<template>
    <div class="move">
        <div class="card1">
            <div class="card"></div>
            <div class="card"></div>
            <div class="card"></div>
            <div class="card"></div>
            <div class="card"></div>
            <div class="card"></div>
            <div class="card"></div>
            <div class="card"></div>
            <div class="card"></div>
            <div class="card"></div>
            <div class="card"></div>
            <div class="card"></div>
            <div class="card"></div>
            <div class="card"></div>
            <div class="card"></div>
        </div>
    </div>

    <button class="btn btn-primary btn-round btn-marquee">
        <span data-text="Marquee">Marquee</span>
    </button>
</template>

<script setup></script>

<style scoped>
.move {
    white-space: nowrap; /* 자식 요소들을 한 줄에 표시하도록 설정 */
    overflow: hidden; /* 애니메이션 요소를 감싸도록 오버플로우 설정 */
    padding: 0;
    margin: 0;
}

.card {
    display: inline-block; /* 인라인 요소로 배치되도록 설정 */
    width: 190px;
    height: 254px;
    background: rgb(223, 225, 235);
    border-radius: 50px;

    margin-right: 10px;
}

.move .card1 {
    animation: move-left 5s linear infinite;
}

@keyframes move-left {
    0% {
        transform: translateX(0%);
    }

    100% {
        transform: translateX(100%);
    }
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
.btn-marquee {
    font-weight: 600;
    border: none;
    overflow: hidden;
    transition: 0.3s;
}

.btn-marquee:hover {
    transform: scale(1.1);
}

.btn-marquee span {
    display: block;
    padding: 0 20px;
    animation: move-left 2s linear infinite;
    position: relative;
}

.btn-marquee span::after {
    position: absolute;
    content: attr(data-text);
    top: 0;
    left: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
}

@keyframes move-left {
    to {
        transform: translateX(-100%);
    }
}
</style>
