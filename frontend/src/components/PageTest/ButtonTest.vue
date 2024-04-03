<template>
    <div class="body">
        <h2 contenteditable="true">Text</h2>
    </div>
</template>

<script setup></script>

<style scoped>
.body {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background: #007bff;
}
h2 {
    position: relative;
    font-size: 6em;
    letter-spacing: 15px;
    color: #bad7f5;
    text-transform: uppercase;
    width: 100%;
    text-align: center;
    -webkit-box-reflect: below 1px linear-gradient(transparent, #000);
    line-height: 0.7em;
    outline: none;
    animation: animate 5s linear infinite;
}
@keyframes animate {
    0% {
        color: #007bff;
        text-shadow: none;
    }
    100% {
        color: #fff;
        text-shadow: #007bff;
    }
}
</style>
