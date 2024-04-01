<template>
    <h1 class="text-center m-5">#실시간 상위 기업</h1>
    <div class="move">
        <div class="card1">
            <div class="card">1</div>
            <div class="card">2</div>
            <div class="card">3</div>
        </div>
        <div class="card2">
            <div class="card">21</div>
            <div class="card">22</div>
            <div class="card">23</div>
        </div>
    </div>
</template>

<script setup></script>

<style scoped>
.move {
    display: flex;
    overflow: hidden;
}

.card {
    width: 190px;
    height: 254px;
    background: rgb(223, 225, 235);
    border-radius: 50px;
    margin-right: 10px;
}

.card1,
.card2 {
    display: flex;
    animation-duration: 5s;
    animation-timing-function: linear;
    animation-iteration-count: infinite;
}

.card1 {
    animation-name: move-left1;
}

.card2 {
    animation-name: move-left2;
}

@keyframes move-left1 {
    from {
        transform: translateX(0%);
    }
    to {
        transform: translateX(-100%);
    }
}

@keyframes move-left2 {
    from {
        transform: translateX(0%);
    }
    to {
        transform: translateX(-100%);
    }
}
</style>
