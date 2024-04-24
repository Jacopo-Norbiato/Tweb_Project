<template>
  <div class="info-admin" v-if="admin">
    <div class="title">
    <h1>INFORMATION</h1>
    <h5>In questa sezione puoi visualizzare tutte le prenotazioni<br/>
      dei rispettivi utenti registrati .</h5>
  </div>

<div class="selection-buttons">
    <div class="buttons-gen">
          <button v-on:click="getAllUsers" style="background: #0b3052">
            Mostra Utenti
          </button>
      </div>
</div>

    <div class="grid-container" v-if="userShow">
      <div class="grid-item" v-for="v in val"> <!-- v-for="v in val" -->
        <i class="fa-solid fa-bookmark"></i>
        <i class="user-id">ID : {{v.id}}, Username : {{v.username}}    /    {{v.nome}} {{v.cognome}}</i>
          <div class="buttons-info">
          <button v-on:click="getInfo(v.id)">
            Mostra / Nascondi prenotazioni
          </button>
          <div v-if="prenotazioniShow && this.idPren === v.id">
          <table class="list-edit">
          <tr>
          <th>#</th><th>GIORNO</th><th>ORA</th><th>DOCENTE</th><th>MATERIA</th><th>Stato</th><th>*</th>
          </tr>
              <tr v-for="(p, index) in prenotazioni">
                <td>{{index}}</td>
                <td>{{p.giorno}}</td>
                <td>{{p.hour}}</td>
                <td>{{p.docente}}</td>
                <td>{{p.corso}}</td>
                <td>{{p.stato}}</td>
                <td v-if="p.executed === 0"><i class="fa-regular fa-star"></i></td>
                <td v-if="p.executed === 1"><i class="fa-solid fa-check"></i></td>
                <td v-if="p.executed === 2"><i class="fa-solid fa-xmark"></i></td>
              </tr>

               </table>
          </div>
        </div>
      </div>

    </div>

  </div>

      <div class="not-info" v-else>
      <div class="title">
      <h1>INFORMAZIONI</h1>

        <i class="fa-solid fa-circle-exclamation"></i>
        <h2>Errore d'accesso</h2>
        <h5>Spiacenti, questa sezione e' riservata ai soli amministratori.<br/>
        Accedi come amministratore per poter prendere visione di tutti i dati <br/>
        contenuti nel database.</h5>
      </div>
    </div>
</template>

<script>
import {ref} from "vue";
export default {
  name: "Information",
  data() {
    return{
      admin: false,
      userShow : false,
      prenotazioniShow: false,
      idPren : null,
      url: 'DemoJac1/InformationController',
      val: [],
      prenotazioni: [],
      buttonTrigger: ref(false),
    }
  },
  mounted() {
      var self = this;
      if (self.$root.session.role === 2) {
        self.admin = true;
       } else {
        alert(" ERROR : per accedere a questa sezione servono i privilegi da Amministratore ")
      }
  },
  methods: {

    getAllUsers() {
      var self = this;

      $.get(this.url, {action: "getUser"}, function (data) {

        console.log(data);
        self.val = data;
        console.log("Dati acquisiti");
        self.userShow = !self.userShow;
      })
    },
    getInfo(idUtente) {
      var self = this;

      $.get(this.url, {action: "getPrenotazioni", id: idUtente}, function (data) {
        self.idPren = idUtente;
        console.log(data);
        self.prenotazioni = data;
        console.log("Dati acquisiti");
        self.prenotazioniShow = !self.prenotazioniShow;
      })
    },
    TogglePopUp () {
        this.buttonTrigger = !this.buttonTrigger;
      }
  }
}
</script>

<style scoped>
.info-admin {
  max-height: fit-content;
  padding-bottom: 50%;
}

.selection-buttons {
  display: inline-flex;
  padding-left: 30%;
}
.buttons-gen {
  padding-left: 7%;
  margin-top: 40px;
}
.buttons-gen button {
    color: white;
    border-radius: 10px;
    transition: .5s;
    height: 50px;
    width: 250px;
    font-size: 20px;
}
.buttons-gen button:active {
  background-color: #0f589d;
  border-radius: 25px;
  border: 2px solid #EEEEEE;
  transform: translateY(-10px);
}
.grid-container{
  display: grid;
  margin-top: 5%;
  padding-left: 20%;
  padding-bottom: 5%;
  margin-bottom: 5%;
  margin-right: 10%;
  column-gap: 30px;
  grid-template-columns: auto; /* questo per selezionare il numero di colonne */

}

.grid-item {
  background: linear-gradient(to bottom, #f3e3c6, #ccad6b);
  align-items: center;
  border-radius: 15px;
  padding: 10px;
  font-size: 20px;
  border: 1px solid #0b3052;
  margin-bottom: 3px;
}
.grid-item i{
  padding-right: 1%;
}

/* ******************************* button laterali ***************** */
.buttons-info {
  padding-left: 20%;
  display: inline;
}

.buttons-info button {
    color: #092a48;
    background: linear-gradient(to right, #c9a155, #e5a63b);
    border-radius: 10px;
    height: 30px;
    width: 250px;
    margin: 5px;
}
.buttons-info button:active {
  background: #0b3052;
  color: white;
  border-radius: 25px;
  border: 2px solid #EEEEEE;
  transition: 0.3s ease;
}


.list-items {
  list-style: none;
  padding-top: 20px;
}
.list-items li {
  border-bottom: 1px solid #0b3052;
  padding-bottom: 10px;
  margin-bottom: 5px;
}
.list-items li:nth-child {

}

</style>