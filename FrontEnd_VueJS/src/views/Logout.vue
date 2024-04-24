<template onload="setInfo">
    <div class="title">
    <h1>LOGOUT</h1>
  </div>
  <div class="logout-sec">
    <h1>Ciao!</h1>

    <h2>Sei sicuro di voler effettuare il logout ?</h2>
    <h2>La tua sessione verrà terminata e non potrai più effettuare prenotazioni o apportare modifiche.</h2>

    <div class="choice">
          <a href="http://localhost:8080/DemoJac1" class="link-to">
            <button class="toggle-btn-logout" id="btn1-logout" v-on:click="invalidateSession">
            Si
          </button>
      </a>
      <router-link to="/agenda" class="link-to"><button class="toggle-btn-logout" id="btn1-logout">Torna all'agenda</button></router-link>
      </div>
  </div>
  </template>

<script>

export default {
  name: "Logout",

  data() {
    return {
      selection: false,
      url: 'DemoJac1/LogoutController'
    }
  },
  methods: {
    invalidateSession() {


      var self = this;
      $.post(this.url, function (data) {

              console.log("Invalida");
              self.$root.session.user = null;
              self.$root.session.role = null;
              self.$root.session.id = null;
              self.$root.session.login = false;

              console.log("qui");

      })
    },
    setInfo() {
      var self = this;
      self.username = self.$root.session.user;
    }
  }
}
</script>

<style scoped>
.logout-sec {
  font-family: "Sitka Text";
  width: content-box;
  padding-top: 2%;
  padding-left: 20%;
  color: white;
}
.logout-sec h1 {
  font-size: 40px;
}
.choice {
  position: center;
  align-items: center;
  padding-left: 10%;
  padding-top: 5%;
  padding-bottom: 50%;
}
.toggle-btn-logout {
    padding: 20px 50px;
    width: 100px;
    cursor: pointer;
    background: transparent;
    border: 1px solid #EEEEEE;
    border-radius: 30%;
    outline: none;
    position: relative;
}

.toggle-btn-logout:hover {
  padding: 30px 60px;
}

#btn1-logout {
    background: linear-gradient(to right, #4e8ec7, #114d83);
    color: white;
    font-size: 30px;
    border-radius: 30px;
    transition: .5s;
    width: fit-content;
    margin: 10px;
}
#btn1-logout:hover {
  font-weight: bold;
}
.link-to {
  font-size: 40px;
  color: #EEEEEE;
  border: transparent;
  box-sizing: border-box;
}
</style>