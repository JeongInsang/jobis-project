<template>
    <div class="cube-container">
        <div class="cube">
            <div class="face front" style="font-size: 2em">
                <!-- <RouterLink style="text-decoration: none" :to="{ name: 'signup' }">J</RouterLink> -->
                L
            </div>
            <div class="face back" style="font-size: 2em">O</div>
            <div class="face right" style="font-size: 2em">G</div>
            <div class="face left" style="font-size: 2em">I</div>
            <div class="face top" style="font-size: 2em">N</div>
            <div class="face bottom" style="font-size: 2em">!</div>
        </div>
    </div>
</template>

<script setup></script>

<style scoped>
.cube-container {
    width: 40px; /* 변경된 너비 */
    height: 40px; /* 변경된 높이 */
    perspective: 160px; /* 조정된 perspective */
    margin: 10px auto; /* 수정된 마진 */
}

.cube {
    position: relative;
    width: 100%;
    height: 100%;
    transform-style: preserve-3d;
    animation: rotate 15s infinite linear;
}

.face {
    position: absolute;
    width: 40px; /* 변경된 너비 */
    height: 40px; /* 변경된 높이 */
    color: rgb(21, 153, 214);
    font-size: 6px; /* 수정된 폰트 크기 */
    text-align: center;
    line-height: 40px; /* 변경된 높이 */
    background: transparent;
    opacity: 0.9;
    border: 1px solid; /* 수정된 테두리 두께 */
    border-image: linear-gradient(
        90deg,
        rgba(14, 8, 110, 1) 0%,
        rgba(15, 15, 167, 1) 26%,
        rgba(20, 45, 255, 1) 85%
    );
    box-shadow: 0 0 20px rgba(8, 178, 245, 0.61); /* 수정된 그림자 크기 */
}

.front {
    transform: translateZ(20px); /* 수정된 Z축 이동 */
    background-color: #0093e9;
    background-image: linear-gradient(160deg, #0093e9 0%, #80d0c7 100%);
}

.back {
    transform: rotateY(180deg) translateZ(20px); /* 수정된 회전 및 Z축 이동 */
    background-color: #0093e9;
    background-image: linear-gradient(160deg, #0093e9 0%, #80d0c7 100%);
}

.right {
    transform: rotateY(90deg) translateZ(20px); /* 수정된 회전 및 Z축 이동 */
    background-color: #0093e9;
    background-image: linear-gradient(160deg, #0093e9 0%, #80d0c7 100%);
}

.left {
    transform: rotateY(-90deg) translateZ(20px); /* 수정된 회전 및 Z축 이동 */
    background-color: #0093e9;
    background-image: linear-gradient(160deg, #0093e9 0%, #80d0c7 100%);
}

.top {
    transform: rotateX(90deg) translateZ(20px); /* 수정된 회전 및 Z축 이동 */
    background-color: #0093e9;
    background-image: linear-gradient(160deg, #0093e9 0%, #80d0c7 100%);
}

.bottom {
    transform: rotateX(-90deg) translateZ(20px); /* 수정된 회전 및 Z축 이동 */
    background-color: #0093e9;
    background-image: linear-gradient(160deg, #0093e9 0%, #80d0c7 100%);
}

.cube-container:hover .cube {
    animation-play-state: paused;
}

@keyframes rotate {
    0% {
        transform: rotateX(0) rotateY(0) rotateZ(0);
    }

    100% {
        transform: rotateX(360deg) rotateY(360deg) rotateZ(360deg);
    }
}
</style>
