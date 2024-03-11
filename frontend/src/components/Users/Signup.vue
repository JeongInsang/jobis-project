<template>
    <div>

    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useFinanceStore } from '@/stores/finance'
  
const store = useFinanceStore()
const username = ref(null)
const password1 = ref(null)
const password2 = ref(null)
const email = ref(null)
const nickname = ref(null)
const job = ref(null)
const age = ref(null)
const money = ref(null)
  
   
  
const signUp = function () {
const payload = {
    username: username.value,
    password1: password1.value,
    password2: password2.value,
    ...(email.value && { email: email.value }),
    ...(nickname.value && { nickname: nickname.value }),
    ...(job.value && { job: job.value }),
    ...(age.value && { age: age.value }),
    ...(money.value && { money: money.value }),
  }
    store.signUp(payload)
}
  
  
</script>

<style scoped>

</style>