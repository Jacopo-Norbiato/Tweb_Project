<script setup>
var year = new Date().getFullYear();
var month = new Date().getMonth()+1;
var day = new Date().getDate();
</script>

<template >
  <div class="title">
    <h1>AGENDA</h1>
    <h5>In questa sezione puoi trovare i tuoi prossimi impegni e lo storico di tutte le tue prenotazioni<br/>
      alla data corrente :
    </h5>
    <h4 style="color: #0b3052">{{day}} / {{month}} / {{year}}</h4>
  </div>



  <div class="container-ag" v-if="userRole">
     <div class="buttons">
      <button v-on:click="getPrenotazioni">
      Mostra prenotazioni
    </button>
    <button v-on:click="nascondi">
     Nascondi la lista
    </button>

    </div>

    <div class="main-div">

      <div class="left">
        <h1>PRENOTAZIONI ATTIVE: </h1>
        <h5 class="popup" style="color: #09263f"> ATTENZIONE !
        <br/>Clicca su EFFETTUATA per segnare la prenotazione come eseguita.
        <br/>Clicca su RIMUOVI per cancellare la prenotazione dalla tua agenda.
        </h5>

        <div class="information" v-if="showGet">
          <div v-for="v in val">
            <div v-if="v.executed === 0" class="ev-det-component">
                 <event-details :giorno="v.giorno" :hour="v.hour" :corso="v.corso" :docente="v.docente" :idPrenAss="v.idPrenAss" :idPrenRip="v.idPrenRipetizione" :idPrenotazione="v.idPrenotazione"></event-details>
            </div>
         </div>
        </div>
      </div>


    <div class="right">
      <div class="information">
        <h1>PRENOTAZIONI PASSATE: </h1>
        <h5 class="popup" style="color: #09263f"> LEGENDA
        <br/>Una prenotazione risulta DISDETTA se non è stata effettuata
         <br/>prima della sua scadenza temporale.
           </h5>
        <div class="prenot-grid">

          <table class="list-edit">
          <tr>
          <th>GIORNO</th><th>ORA</th><th>DOCENTE</th><th>MATERIA</th><th>Stato</th><th>*</th>
          </tr>
              <tr v-for="v in val">
                <td v-if="v.executed === 1 || v.executed === 2">{{v.giorno}}</td>
                <td v-if="v.executed === 1 || v.executed === 2">{{v.hour}}</td>
                <td v-if="v.executed === 1 || v.executed === 2">{{v.docente}}</td>
                <td v-if="v.executed === 1 || v.executed === 2">{{v.corso}}</td>
                <td v-if="v.executed === 1 || v.executed === 2">{{v.stato}}</td>
                <td v-if="v.executed === 1"><i class="fa-solid fa-check"></i></td>
                <td v-if="v.executed === 2"><i class="fa-solid fa-xmark"></i></td>
              </tr>



          </table>
        </div>

      </div>
    </div>

  </div>

  </div>
</template>

<script>

import EventDetails from '../components/EventDetails.vue';

export default {
  name: "Agenda",
  components: {
    EventDetails,
  },
  data() {
    return{
      url: 'DemoJac1/AgendaController',
      showGet: true,
      val: [],
      userRole: false,
    }
  },
   mounted() {
      var self = this;
      if (self.$root.session.role === 1) {
        self.userRole = true;
       } else {
        alert(" ERROR : per accedere a questa sezione devi essere registrato come User.")
      }
  },
  methods: {
    getPrenotazioni() {

      console.log("## getPrenotazioni()");

      var self = this;
      if(self.$root.session.login === true) {
        self.showGet= true;
        console.log("carico i dati");
        $.get(this.url, {id: self.$root.session.id}, function (data) {
          console.log(data);
          self.val = data;
      })
      } else {
        alert("Devi effettuare il login per visualizzare lo stato delle tue prenotazioni");
      }
    },
    nascondi() {
      this.showGet = false;
    }
  }
};
</script>

<style>

.title {
  padding-bottom: 5%;
}
.buttons {
  padding-left: 15%;
  display: flex;
}
.buttons button {
    background: linear-gradient(to right, #6a8ca9, #0a4d8a);
    border-radius: 10px;
    transition: .5s;
    height: 40px;
    width: 35%;
    color: #EEEEEE;
    margin: 15px;
    font-size: 20px;
}
.buttons button:hover {
  border-radius: 25px;
  border: 3px solid white;
}
.container-ag {
    padding-top: 2%;
    justify-content: center;
    color: #483307;
    width: 100%;
    padding-left: 15%;
    padding-bottom: 10%;
    margin-right: 5%;
    font-family: Bahnschrift;
}
.container-ag h1 {
  font-size: 30px;
}

.main-div {
  display: flex;
  width: 100%;
  margin-left: 3%;
  margin-right: 5%;
  margin-top: 3%;
}
.left {
  height: fit-content;
  border: 3px solid #0b3052;
  border-radius: 15px;
  margin-right: 2%;
  padding: 3%;
}
.right {
  border: 3px solid #f8ad1d;
  border-radius: 15px;
  padding: 3%;
  height: fit-content;
}
.information {
  margin: 10px;
  font-size: 25px;
}

.ev-det-component {
  margin-bottom: 15px;
}
.right:hover, .left:hover {
  transition: all 0.2s ease-in;
  transform: translateX(10px);
}
.list-edit {
  font-size: 16px;
}
.list-edit tr {
  margin-top: 10px;
  border-bottom: 1px solid #000000;
}

.list-edit tr:nth-child(even) {
  background-color: #f1c667;
}
</style>