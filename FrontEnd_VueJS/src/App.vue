<template>
  <nav-bar/>
  <router-view></router-view>
</template>

<script>
import NavBar from "./components/NavBar.vue";
import router from "./router/index"
import RegistrationForm from "@/components/RegistrationForm.vue";
export default {
  name: "App",
  data() {
    return {
      session: {
        id: null,
        role: null,
        login: false,
        user: null,
      }
    }
  },
  components: {
    RegistrationForm,
    NavBar,
  }
}
</script>

<style>
body {
  background-color: #EEEEEE;
  font-family: 'Montserrat', sans-serif;
  display: grid;
}
</style>