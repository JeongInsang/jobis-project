<template>
    <h2 style="text-align: center; margin: 3em">#조회수 상위 기업</h2>
    <div class="container bank-logos" style="height: 300px">
        <div class="row g-5 overflow-hidden">
            <div class="col">
                <div
                    class="card d-flex flex-column justify-content-center align-items-center"
                    style="height: 12em; width: 12em">
                    <img style="height: 5em" src="@/assets/img/mechanic.png" alt="" />
                    <h3 class="card-text text-center">제조업</h3>
                </div>
            </div>
            <div class="col">
                <div
                    class="card d-flex flex-column justify-content-center align-items-center"
                    style="height: 12em; width: 12em">
                    <img style="height: 5em" src="@/assets/img/mechanic.png" alt="" />
                    <h3 class="card-text text-center">정보통신업</h3>
                </div>
            </div>
            <div class="col">
                <div
                    class="card d-flex flex-column justify-content-center align-items-center"
                    style="height: 12em; width: 12em">
                    <img style="height: 5em" src="@/assets/img/mechanic.png" alt="" />
                    <h3 class="card-text text-center">과학 및 기술</h3>
                </div>
            </div>
            <div class="col">
                <div
                    class="card d-flex flex-column justify-content-center align-items-center"
                    style="height: 12em; width: 12em">
                    <img style="height: 5em" src="@/assets/img/mechanic.png" alt="" />
                    <h3 class="card-text text-center">도/소매</h3>
                </div>
            </div>
            <div class="col">
                <div
                    class="card d-flex flex-column justify-content-center align-items-center"
                    style="height: 12em; width: 12em">
                    <img style="height: 5em" src="@/assets/img/mechanic.png" alt="" />
                    <h3 class="card-text text-center">건설업</h3>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup></script>

<style scoped>
.bank-logos {
    /* display: flex; */
    animation: moveLeftRight 30s linear infinite; /* 좌우로 이동하는 애니메이션 적용 */
}

@keyframes moveLeftRight {
    0% {
        transform: translateX(0);
        opacity: 1;
    }
    20% {
        transform: translateX(-100%);
        opacity: 0;
    }
    25% {
        transform: translateX(-100%);
        opacity: 0;
    }
    45% {
        transform: translateX(0%);
        opacity: 1;
    }
    55% {
        transform: translateX(50%);
        opacity: 1;
    }
    70% {
        transform: translateX(100%);
        opacity: 0;
    }
    80% {
        transform: translateX(120);
        opacity: 0;
    }
    100% {
        transform: translateX(0);
        opacity: 1;
    }
}
</style>
