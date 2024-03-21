<template>
    <div
        style="
            border: solid 1px black;
            display: flex;
            justify-content: center;
            height: 800px;
            align-items: center;
        ">
        <div class="card">
            <button class="mail">
                <div class="mail-icon"></div>
            </button>
            <div class="profile-pic">
                <img style="height: 280p; width: 280px" src="@/img/company1.png" alt="company1" />
            </div>
            <div class="bottom">
                <div class="content">
                    <span class="name">회사명 1</span>
                    <span class="about-me">연봉 : </span>
                    <span class="about-me">지역 : </span>
                </div>
                <div class="bottom-bottom">
                    <div class="social-links-container">
                        <!-- <div class="social-link"></div>
                        <div class="social-link"></div>
                        <div class="social-link"></div> -->
                    </div>
                    <button class="button">선택하기</button>
                </div>
            </div>
        </div>
        <div style="font-size: 7em">VS</div>
        <div class="card">
            <button class="mail">
                <div class="mail-icon"></div>
            </button>
            <div class="profile-pic">
                <img style="height: 280p; width: 280px" src="@/img/company2.png" alt="company1" />
            </div>
            <div class="bottom">
                <div class="content">
                    <span class="name">회사명 2</span>
                    <span class="about-me">연봉 : </span>
                    <span class="about-me">지역 : </span>
                </div>
                <div class="bottom-bottom">
                    <div class="social-links-container">
                        <!-- <div class="social-link"></div>
                        <div class="social-link"></div>
                        <div class="social-link"></div> -->
                    </div>
                    <button class="button">선택하기</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup></script>

<style scoped>
.card {
    width: 280px;
    height: 280px;
    background: white;
    border-radius: 32px;
    padding: 3px;
    position: relative;
    box-shadow: #604b4a30 0px 70px 30px -50px;
    transition: all 0.5s ease-in-out;
}

.card .mail {
    position: absolute;
    right: 2rem;
    top: 1.4rem;
    background: transparent;
    border: none;
}

.card .mail .mail-icon {
    width: 24px;
    height: 24px;
    background-color: transparent;
    border: 2px solid #007bff;
    border-radius: 4px;
}

.card .profile-pic {
    position: absolute;
    width: calc(100% - 6px);
    height: calc(100% - 6px);
    top: 3px;
    left: 3px;
    border-radius: 29px;
    z-index: 1;
    border: 0px solid #007bff;
    overflow: hidden;
    transition:
        all 0.5s ease-in-out 0.2s,
        z-index 0.5s ease-in-out 0.2s;
}

.card .profile-pic:hover {
    transform: scale(1.3);
}

.card .bottom {
    position: absolute;
    bottom: 3px;
    left: 3px;
    right: 3px;
    background: #007bff;
    top: 80%;
    border-radius: 29px;
    z-index: 2;
    box-shadow: rgba(96, 75, 74, 0.1882352941) 0px 5px 5px 0px inset;
    overflow: hidden;
    transition: all 0.5s cubic-bezier(0.645, 0.045, 0.355, 1) 0s;
}

.card .bottom .content {
    position: absolute;
    bottom: 0;
    left: 1.5rem;
    right: 1.5rem;
    height: 160px;
}

.card .bottom .content .name {
    display: block;
    font-size: 1.2rem;
    color: white;
    font-weight: bold;
}

.card .bottom .content .about-me {
    display: block;
    font-size: 0.9rem;
    color: white;
    margin-top: 1rem;
}

.card .bottom .bottom-bottom {
    position: absolute;
    bottom: 1rem;
    left: 1.5rem;
    right: 1.5rem;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.card .bottom .bottom-bottom .social-links-container {
    display: flex;
    gap: 1rem;
}

.card .bottom .bottom-bottom .social-link {
    width: 20px;
    height: 20px;
    background-color: white;
    border-radius: 50%;
    filter: drop-shadow(0 5px 5px rgba(165, 132, 130, 0.1333333333));
}

.card .bottom .bottom-bottom .social-link:hover {
    background-color: #f55d56;
    transform: scale(1.2);
}

.card .bottom .bottom-bottom .button {
    background: white;
    color: #007bff;
    border: none;
    border-radius: 20px;
    font-size: 0.6rem;
    padding: 0.4rem 0.6rem;
    box-shadow: rgba(165, 132, 130, 0.1333333333) 0px 5px 5px 0px;
}

.card .bottom .bottom-bottom .button:hover {
    background: #f55d56;
    color: white;
}

.card:hover {
    border-top-left-radius: 55px;
}

.card:hover .bottom {
    top: 20%;
    border-radius: 80px 29px 29px 29px;
    transition: all 0.5s cubic-bezier(0.645, 0.045, 0.355, 1) 0.2s;
}

.card:hover .profile-pic {
    width: 100px;
    height: 100px;
    aspect-ratio: 1;
    top: 10px;
    left: 10px;
    border-radius: 50%;
    z-index: 3;
    border: 7px solid #007bff;
    box-shadow: rgba(96, 75, 74, 0.1882352941) 0px 5px 5px 0px;
    transition:
        all 0.5s ease-in-out,
        z-index 0.5s ease-in-out 0.1s;
}
</style>
