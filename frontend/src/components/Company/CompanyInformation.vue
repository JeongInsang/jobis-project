<template>
    <div class="company-info">
        <h1>Company Information</h1>
        <div class="info-card" v-if="companyData">
            <img :src="companyData.logo" :alt="companyData.name" class="company-logo" />
            <div class="details">
                <h2>{{ companyData.name }}</h2>
                <p>{{ companyData.description }}</p>
                <div class="additional-info">
                    <div><strong>Industry:</strong> {{ companyData.industry }}</div>
                    <div><strong>Founded:</strong> {{ companyData.founded }}</div>
                    <div><strong>CEO:</strong> {{ companyData.ceo }}</div>
                </div>
            </div>
        </div>
        <div v-else>
            <p>Loading...</p>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const companyData = ref(null);
const accessToken = localStorage.getItem('accessToken');

onMounted(() => {
    getCompanyDetail();
});

const getCompanyDetail = () => {
    axios({
        method: 'get',
        url: 'YOUR_API_ENDPOINT',
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            companyData.value = res.data;
        })
        .catch((err) => {
            console.error(err);
        });
};
</script>

<style scoped>
.company-info {
    text-align: center;
}

.info-card {
    width: 30rem;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    padding: 20px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    background-color: #f9f9f9;
}

.company-logo {
    width: 100px;
    height: 100px;
    object-fit: contain;
    border-radius: 50%;
    margin-right: 20px;
}

.details {
    text-align: left;
}

.additional-info {
    margin-top: 10px;
}

.additional-info div {
    margin-bottom: 5px;
}
</style>
