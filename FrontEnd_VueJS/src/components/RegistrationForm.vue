<template>
  <div class="form-box" style="font-weight: bold">
    <h1>Registrati qui!</h1>
    <p>Compila i campi seguenti per creare
      <br>un account e navigare nel calendario.
    </p>

    <form id = "registerForm" class="input-group">
      <div class="input-box">
        <input v-html="nome" type="text" class="input-field" size="15" id="name" required name="Name" placeholder="Name">
        <input v-html="cognome" type="text" class="input-field" size="15" id="surname" required name="Surname" placeholder="Surname">
      </div>
      <div class="input-box">
        <input type="text" class="input-field" size="15" id="us_name" required name="Username" placeholder="Username">
      </div>
      <div class="input-box">
        <input type="password" class="input-field" size="15" id="pword" required name="Pword" placeholder="Password">
        <input type="password" class="input-field" size="15" id="pword1" required name="Pword" placeholder="Confirm Password">
      </div>

      <input class="button1" type="button" id="button2" value="Register" v-on:click="register"/>
      <div id="space">
        <div class="result1">
         {{res}}
        </div>
      </div>
    </form>
  </div>

</template>

<script>
export default {
  name: "RegistrationForm",
  data() {
    return {
      title: "HELLLOO",
      na: "",
      su: "",
      pw: "",
      res: "",
      username: "",
      xhr: null,
    }
  },
  methods: {
    //questa funzione viene chiamata da button con "v-on"!
    register() {
      this.title = "Change Title";
      this.na = document.getElementById("name").value;
      this.su = document.getElementById("surname").value;
      this.pw = document.getElementById("pword").value;
      this.username = document.getElementById("us_name").value;

      let url = "DemoJac1/RegistrationController";

      $.post(url, {name: this.na, surname: this.su, password: this.pw, us_name: this.username}, function(data) {

        this.res = data;
        console.log("Chiamata alla servlet registration eseguita correttamente");
        alert("Esito REGISTRATION SERVLET : " + data);
        let appear = document.querySelector(".result1");
        appear.classList.toggle("active");
      })
      },
    }
}


</script>

<style scoped>

.input-box {
  display: flex;
  margin: 5px;
}
label {
  color: white;
  font-family: "Times New Roman";
}

select {
    width: 70%;
    padding: 3px 1px;
    text-align: center;
    margin: 3px 0;
    border: 1px solid #93bbe7;
    outline: none;
    border-radius: 10px;
    background: #efe9e9;

}
.input-group {
    top: 180px;
    width: 300px;
    position: absolute;
    alignment: center;
    transition: .5s;
}

.input-field {
    width: 100%;
    padding: 6px 2px;
    text-align: center;
    margin: 3px 1px;
    border: 1.5px solid #93bbe7;
    outline: none;
    font-size: 15px;
    border-radius: 20px;
    background: #efe9e9;
}


#registerForm {
    left: 50px;
}
</style>