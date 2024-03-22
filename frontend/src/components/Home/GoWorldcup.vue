<template>
    <div class="imgcontainer">
        <button type="button" class="btn btn-primary btn-lg">기업선택하기</button>
    </div>
</template>

<script setup></script>

<style scoped>
.imgcontainer {
    display: flex;
    position: relative; /* 자식 요소의 위치를 설정하기 위해 부모 요소를 상대적으로 설정합니다. */
    height: 300px;
    width: 100%;
    background-image: url('@/img/banner.png');
    background-size: contain; /* 혹은 background-size: cover; */
    background-position: center;
    background-repeat: no-repeat;
}

.btn {
    position: absolute; /* 절대 위치를 설정합니다. */
    right: 15rem; /* 오른쪽으로 120px 만큼 옮깁니다. */
    top: 50%; /* 부모 요소의 세로 중앙에 위치하도록 설정합니다. */
    transform: translateY(-50%); /* 부모 요소의 세로 중앙에 정렬합니다. */
}
</style>
