<script setup>
import {ref} from 'vue'

var transit = ref(true);
</script>

<template>
    <div class="title">
    <h1>WELCOME!</h1>
   </div>
  <div class="form-box" style="font-weight: bold">
      <div id="btn1">
        <button class="toggle-btn" v-on:click="transit = !transit">Clicca qui.</button>
      </div>

    <div v-if="transit"><login-form title="Login"></login-form></div>
    <div v-else><RegistrationForm></RegistrationForm></div>

  </div>
</template>


<script>

import LoginForm from '../components/LoginForm.vue';
import RegistrationForm from '../components/RegistrationForm.vue';
export default {
  name: "Home",
  components: {
    RegistrationForm, LoginForm
  },
  data: function () {
    return {
      }
  },
  methods: {

  }
}
</script>
<style>


.form-box {
    width: 380px;
    height: 100vh;
    position: relative;
    margin: 5% auto;
    padding: 5px;
    background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(63, 60, 60, 0.4)), url(@/assets/library.jpg);
    overflow: hidden;
    background-size: cover;
    transition: all .5s ease;
}

.toggle-btn {
    padding: 10px 30px;
    cursor: pointer;
    background: transparent;
    border-radius: 15px;
    outline: none;
    position: relative;
}
.toggle-btn:hover {
  border: 2px solid #EEEEEE;
  border-radius: 25px;
}
#btn1 {
    background: linear-gradient(to right, #8ba7c0, #1e72be);
    border-radius: 30px;
    transition: .5s;
    width: fit-content;
    margin: 10px;
}

img {
    mix-blend-mode: multiply;
    margin-left: 10px;
    margin-top: 300px;
    height: 30px;
}

</style>