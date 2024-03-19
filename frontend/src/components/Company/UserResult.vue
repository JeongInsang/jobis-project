<template>
    <div class="section">
        <div class="container">
            <div class="title">김구직님 AHP 분석 결과</div>
        </div>
        <div class="list">
            <div class="row">
                <div class="article">
                    <div class="image-container">
                        <div class="image">
                            <!-- <img class="image-6" src="image-60.png" /> -->
                        </div>
                    </div>
                    <div class="frame-427318906">
                        <div class="title2">성장성 : 50%</div>
                        <div class="subtitle">수익성 : 26%</div>
                        <div class="subtitle">안정성 : 13%</div>
                        <div class="subtitle">시장가치비율 : 7%</div>
                        <div class="subtitle">활동성 : 3%</div>
                    </div>
                </div>
            </div>
        </div>
        <svg
            class="vector-200"
            width="1440"
            height="1"
            viewBox="0 0 1440 1"
            fill="none"
            xmlns="http://www.w3.org/2000/svg">
            <path d="M0 1H1440" stroke="black" stroke-opacity="0.1" />
        </svg>
    </div>
</template>
<script>
export default {
    name: 'Section',
    components: {},
    props: {},
    data() {},
};
</script>
<style scoped>
.section,
.section * {
    box-sizing: border-box;
}
.section {
    padding: 60px 170px 60px 170px;
    display: flex;
    flex-direction: row;
    gap: 60px;
    align-items: center;
    justify-content: center;
    align-self: stretch;
    flex-shrink: 0;
    height: 600px;
    position: relative;
    overflow: hidden;
}
.container {
    display: flex;
    flex-direction: column;
    gap: 24px;
    align-items: flex-start;
    justify-content: flex-start;
    flex: 1;
    position: relative;
}
.title {
    color: #000000;
    text-align: left;
    font-family: 'Roboto-Bold', sans-serif;
    font-size: 40px;
    line-height: 48px;
    font-weight: 700;
    position: relative;
    align-self: stretch;
}
.list {
    padding: 20px 0px 20px 0px;
    display: flex;
    flex-direction: column;
    gap: 40px;
    align-items: center;
    justify-content: center;
    flex: 1;
    position: relative;
}
.row {
    display: flex;
    flex-direction: row;
    gap: 40px;
    align-items: center;
    justify-content: flex-start;
    align-self: stretch;
    flex-shrink: 0;
    position: relative;
}
.article {
    border-radius: 6px;
    border-style: solid;
    border-color: rgba(0, 0, 0, 0.1);
    border-width: 1px;
    padding: 16px;
    display: flex;
    flex-direction: row;
    gap: 16px;
    align-items: flex-start;
    justify-content: center;
    flex: 1;
    position: relative;
}
.image-container {
    display: flex;
    flex-direction: row;
    gap: 0px;
    align-items: flex-start;
    justify-content: flex-start;
    flex-shrink: 0;
    width: 200px;
    height: 200px;
    position: relative;
    overflow: hidden;
}
.image {
    background: rgba(217, 217, 217, 0.5);
    align-self: stretch;
    flex: 1;
    position: relative;
}
.image-6 {
    width: 198px;
    height: 200px;
    position: absolute;
    left: 1px;
    top: 0px;
    object-fit: cover;
}
.frame-427318906 {
    display: flex;
    flex-direction: column;
    gap: 8px;
    align-items: flex-start;
    justify-content: flex-start;
    flex: 1;
    position: relative;
}
.title2 {
    color: #000000;
    text-align: left;
    font-family: 'Roboto-Medium', sans-serif;
    font-size: 20px;
    line-height: 28px;
    font-weight: 500;
    position: relative;
    align-self: stretch;
}
.subtitle {
    color: #000000;
    text-align: left;
    font-family: 'Roboto-Medium', sans-serif;
    font-size: 20px;
    line-height: 28px;
    font-weight: 500;
    position: relative;
    align-self: stretch;
}
.vector-200 {
    flex-shrink: 0;
    height: 0px;
    position: absolute;
    right: 0px;
    left: 0px;
    bottom: 0px;
    overflow: visible;
}
</style>
