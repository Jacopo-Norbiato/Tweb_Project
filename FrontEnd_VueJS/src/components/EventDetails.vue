<template>

<Transition name="nested">
    <div class="division" v-if="delElement">

      <div class="project">
        <ul>
        <i class="fa-regular fa-clock" v-on:click="send"><i id="details-test" style="padding-left: 10px"> {{this.giorno}}, orario:  {{this.hour}} </i></i>
        </ul>
        <ul>
          <i class="fa-regular fa-user" v-on:click="send"><i id="details-test" style="padding-left: 10px">Docente:   {{docente}}</i></i>
        </ul>
        <ul>
          <i class="fa-regular fa-bookmark" v-on:click="send"><i id="details-test" style="padding-left: 10px">Corso:   {{corso}}</i></i>
        </ul>
      </div>


    <div class="actions">
    <ul>
      <li class="content">
        <button v-on:click="remove(idPrenotazione, idPrenRip)"><i class="fa fa-times"></i>Rimuovi Prenotazione
        </button>
      </li>
      <li class="content">
        <button v-on:click="check(idPrenRip)"><i class="fa fa-check"></i>Segna come Effettuata
        </button>
      </li>
    </ul>
  </div>
      <div class="lat" v-if="show">
        <h1>content</h1>
      </div>
  </div>
  </Transition>


</template>

<script>
export default {
  name: "EventDetails",
  props: {
    giorno: String,
    hour: String,
    docente: String,
    corso: String,
    idPrenotazione: String,
    idPrenRip: String,
    idPrenAss: String,
  },
  data() {
    return {
    show: false,
    delElement: true,
    risposta: null,
    url: 'DemoJac1/AgendaController',
    action: "",
    }
  },
  methods: {
    send() {
      let self = this;
      if(self.$root.session.login) {
        alert("user: " + self.$root.session.user + " : RICONOSCIUTO");
      } else {
        alert("SPIACENTE: operazione non possibile perchè non hai fatto il login");
      }
    },

    remove(id1, id2) {
      var self = this;
      self.action = "REMOVE";
      console.log("++ remove() -> user id : " + self.$root.session.id);
      console.log("valore passato id1 : " + id1 + " id2 : " + id2);
      $.post(this.url, {action: self.action, idPrenUser: self.$root.session.id, idPren: id1, idPrenRip: id2}, function (data) { //faccio la post e aggiungo la seguente prenotazione nelle ripetizioni dell'utente

          console.log(data);
          self.risposta = data;
          console.log("prima di deleted in EventDetails");
          self.delElement = false;
          alert(self.risposta);
      })

    },
    check(id1) {
      var self = this;
      self.action = "CHECK";
      console.log("++ check() -> user id : " + self.$root.session.id);
      console.log("valore passato id1 : " + id1);
      $.post(this.url, {action: self.action, idPrenUser: self.$root.session.id, idPrenRip: id1}, function (data) {
          console.log(data);
          self.risposta = data;
           self.delElement = false;
          alert(self.risposta + "\n Clicca su 'Mostra Ripetizioni' per visualizzare i nuovi dati");
      })
    },
  }

};
</script>

<style>

.project {
  margin-bottom: 10px;
  background-color: white;
  padding: 5px 20px;
  border-radius: 10px;
  border-left: 15px solid #eaab28;
  font-size: 20px;
  padding-bottom: 3%;
}

#details-test {
  font-family: "Times New Roman", sans-serif;
  font-size: 18px;
}

.actions {
  margin-left: 20px;
}

.actions li {
  display: inline;
}

.actions i {
  margin-left: 5px;
  flex: border-box;
  padding-bottom: 5px;
}

.content {
  margin-right: 5%;
  padding-bottom: 3px;
}
.content button {
  color: white;
  background-color: transparent;
  font-size: 10px;
  text-align: center;
  border-radius: 10px;
  margin-bottom: 5px;
  width: fit-content;
  border: 1px solid #EEEEEE;
}
.content button i {
  padding-right: 5%;
  padding-top: 3%;
}
.content button:hover {
  background-color: #eaab28;
  border-radius: 25px;
  border: 3px solid #EEEEEE;
}

.division {
    background-color: #1c78b7;
    border-radius: 10px;
    margin-left: 2%;
    margin-top: 2%;
    box-shadow: 10px 10px #0b3052;
    width: fit-content;
    border: 1px solid #EEEEEE;
}


/* ************************************************************ <Transition> *************** */
.nested-enter-active, .nested-leave-active {
	transition: all 0.6s ease-in-out;
}
/* delay leave of parent element */
.nested-leave-active {
  transition-delay: 0.25s;
}

.nested-enter-from,
.nested-leave-to {
  transform: translateX(30px);
  opacity: 0;
}

/* we can also transition nested elements using nested selectors */
.nested-enter-active .division,
.nested-leave-active .division {
  transition: all 0.3s ease-in-out;
}
/* delay enter of nested element */
.nested-enter-active .division {
	transition-delay: 0.25s;
}

.nested-enter-from .division,
.nested-leave-to .division {
  transform: translateX(30px);
  opacity: 0.001;
}


</style>