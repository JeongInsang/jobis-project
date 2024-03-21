<template>
    <div class="carousel">
        <div class="background-image"><img src="" alt="" /></div>
        <div class="title1">당신은</div>
        <div class="typing-animation" id="typing-animation"></div>
        <div class="title2">될 수 있습니다.</div>
    </div>
</template>

<script setup>
import 'vue3-carousel/dist/carousel.css';

// 타이핑할 문자열 배열
const texts = ['Engineer', 'Programmer', 'Doctor'];

// 현재 텍스트 인덱스
let textIndex = 0;

// 타이핑 애니메이션 함수
function typeWriter(text, i, typingAnimation) {
    if (i < text.length) {
        typingAnimation.innerHTML += text.charAt(i);
        i++;
        setTimeout(function () {
            typeWriter(text, i, typingAnimation);
        }, 100); // 다음 글자 타이핑 전 대기 시간 (milliseconds)
    } else {
        // 텍스트가 모두 타이핑되면 다음 텍스트로 이동
        setTimeout(function () {
            deleteWriter(text, text.length - 1, typingAnimation);
        }, 1000); // 텍스트가 모두 타이핑된 후 대기 시간 (milliseconds)
    }
}

// 삭제 애니메이션 함수
function deleteWriter(text, i, typingAnimation) {
    if (i >= 0) {
        const newText = text.substring(0, i);
        typingAnimation.innerHTML = newText;
        i--;
        setTimeout(function () {
            deleteWriter(text, i, typingAnimation);
        }, 50); // 다음 글자 삭제 전 대기 시간 (milliseconds)
    } else {
        // 삭제가 모두 완료되면 다음 텍스트로 이동
        textIndex = (textIndex + 1) % texts.length;
        setTimeout(function () {
            typeWriter(texts[textIndex], 0, typingAnimation);
        }, 500); // 텍스트 삭제 후 대기 시간 (milliseconds)
    }
}

// 페이지 로드 시 타이핑 애니메이션 시작
document.addEventListener('DOMContentLoaded', function () {
    const typingAnimation = document.getElementById('typing-animation');
    if (typingAnimation) {
        typeWriter(texts[textIndex], 0, typingAnimation);
    }
});
</script>

<style scoped>
.carousel {
    position: relative;
    width: 100%;
    height: 700px; /* 적절한 높이로 조정하세요 */
}

.background-image {
    position: absolute;
    top: 0;
    left: 0;
    right: 0; /* 오른쪽 정렬을 해제합니다. */
    bottom: 0; /* 아래쪽 정렬을 해제합니다. */
    margin: auto; /* 수평, 수직 가운데 정렬을 위해 margin을 auto로 설정합니다. */
    margin-top: 3%;
    width: 90%;
    height: 100%;
    background-image: url('@/img/gif1.gif'); /* 백그라운드 이미지 경로를 설정하세요 */
    background-size: cover;
    background-position: center;
    z-index: -1; /* 타이핑 메시지 위에 오도록 배치 */
    /* 애니메이션 효과 추가 */
    animation: zoom-in 5s infinite alternate;
}

.typing-animation {
    font-size: 3em;
    color: yellow;
    font-family: Arial, sans-serif;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    overflow: hidden; /* 텍스트 넘치는 부분 숨기기 */
    white-space: nowrap; /* 텍스트가 한 줄로 줄 바꿈되지 않도록 함 */
    animation:
        typing 2s steps(20, end),
        blink-caret 0.75s step-end infinite; /* 타이핑 애니메이션과 커서 깜빡임 애니메이션 적용 */
}

.typing-animation::after {
    content: '';
    border-left: 2px solid white; /* 커서 선 스타일 및 색상 설정 */
    margin-left: 5px; /* 커서와 텍스트 사이의 간격 조정 */
    animation: blink-caret 0.75s step-end infinite; /* 커서 깜빡임 애니메이션 적용 */
}

@keyframes typing {
    from {
        width: 0;
    }
    to {
        width: 100%;
    }
}

@keyframes blink-caret {
    from,
    to {
        border-color: transparent;
    }
    50% {
        border-color: white; /* 흰색으로 변경 */
    }
}
.title1 {
    font-size: 4em;
    color: white;
    position: absolute;
    top: 45%;
    left: 50%;
    transform: translate(-50%, -150%); /* 타이핑 텍스트의 상단에서 조금 위에 위치하도록 조정 */
    text-align: center; /* 가운데 정렬 */
    width: 100%; /* 부모 요소인 .carousel에 맞게 너비 설정 */
    /* -webkit-text-stroke: 1px white; 텍스트 테두리 색상과 너비 설정 */
}

.title2 {
    font-size: 4em;
    color: white;
    position: absolute;
    top: 75%;
    left: 50%;
    transform: translate(-50%, -150%); /* 타이핑 텍스트의 상단에서 조금 위에 위치하도록 조정 */
    text-align: center; /* 가운데 정렬 */
    width: 100%; /* 부모 요소인 .carousel에 맞게 너비 설정 */
}
</style>
