<script setup>
import {ref} from "vue";

</script>

<template>
    <p>Benvenuto!
    <br>Entra nell'area personale per cominciare.
    <br>Se non sei già registrato,
    <router-link to="/registration" style="color: rgba(114,137,234,0.98)"><i class="fas fa-sign-in"></i>   Registrati qui.</router-link>
    </p>
  <form id = "loginForm" class="input-group">
     <input type="text" class="input-field" size="15" id="username" required name="Username" placeholder="Username">
     <input type="password" class="input-field" size="15" id="pw" required name="Pword" placeholder="Password">
     <input class="button1" type="button" id="button1" value="Login" v-on:click="login"/>
     <div id="space">
        <div class="result">
        {{res}}
        </div>
     </div>
  </form>

</template>

<script>
export default {
  name: "LoginForm",
  data() {
    return {
      res: "",
      val: [],
      url: "DemoJac1/Login",
      account: "",
      pw: "",
      altreInfo: '',
      xhrObj: new XMLHttpRequest(),
    }
  },
  methods: {
    login() {

      this.account = document.getElementById("username").value;
      this.pw = document.getElementById("pw").value;

      var self = this;

      if(self.$root.session.login === false) { //se alcun utente non ha gia' effettuato il login

        $.get(this.url, {username: this.account, password: this.pw}, function (data) {

         console.log("Qua dopo aver chiamato la get passando i valori");
         console.log(data)
         self.val = data;

          self.$root.session.id = self.val[0];
          self.$root.session.role = self.val[1];
          self.$root.session.login = true;
          self.$root.session.user = self.account;

          if(self.val[0] !== undefined && self.val[1] !== undefined) {
            self.res = "Login effettuato!!";
          } else {
            self.res = "Ops, qualcosa è andato storto!";
          }
          let appear = document.querySelector(".result");
          appear.classList.toggle("active");
          console.log("id : " + self.$root.session.id + ", role: " + self.$root.session.role + " username : " + self.$root.session.user);

        })
      } else { //un utente ha gia' fatto login
        alert("ERRORE : Prima di registrarti con le credenziali di un altro utente, devi prima effettuare il logout.")
      }


    },
  }
}
</script>

<style scoped>

.input-group {
    margin: 35px;
    top: 180px;
    width: 220px;
    position: absolute;
    alignment: center;
    transition: .5s;
}
.input-field {
    width: 100%;
    padding: 8px 2px;
    text-align: center;
    margin: 3px 0;
    border: 1.5px solid #93bbe7;
    outline: none;
    border-radius: 20px;
    background: #efe9e9;
}

#loginForm {
  left: 50px;
}

</style>