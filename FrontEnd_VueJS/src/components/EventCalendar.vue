<template>
<Transition name="fade">
<div class="section" v-if="deleted">
  <div class="line">
    <h4>
      > Orario : {{this.hour}}<br/>
      > Docente: {{this.docente}} <br/>
      > Corso : {{this.corso}}</h4>
  </div>
  <div class="actions">
       <div class="line" v-if="checkDate()">
        <button v-on:click="add">
          <i class="fa fa-plus-circle"></i>Add this event
        </button>
       </div>
  </div>
</div>
  </Transition>
</template>

<script>

export default {
  name: "EventCalendar",
  props: {
    day: String,
    hour: String,
    docente: String,
    corso: String,
    idRip: String,
    idAss: String
  },
  data() {
    return{
        risposta: null,
        deleted: true,
        val: [],
        url: 'DemoJac1/ReservationController',
      }
    },
  methods: {
    add() {
      var self = this;
      console.log("## add() in EventCalendar");

      if(self.$root.session.login === true) { //ossia se e' stato effettuato il login
        $.post(this.url, {idUser: self.$root.session.id, idRipetizione: this.idRip, idAssociazione: this.idAss}, function (data) { //faccio la post e aggiungo la seguente prenotazione nelle ripetizioni dell'utente

          console.log(data);
          self.risposta = data;
          self.deleted = false;
          alert(self.risposta);
          })

        //  this.deleted=false;
       } else {
         this.send();
       }
    },
    send(){
      alert("ATTENZIONE! \nDevi effettuare il Login per prenotare la ripetizione desiderata. Torna alla pagina iniziale e accedi con le tue credenziali.");
    },
    checkDate() {

      var today = new Date();
      var dayRip = new Date(this.day);

      if(dayRip >= today) {
        return true;
      } else {
        return false;
      }
    }
  }
}
</script>

<style scoped>
.section {
  margin: 15px;
  color: #ffffff;
  background: linear-gradient(to right, rgb(180, 129, 16), #eab54a);
  border-radius: 10px;
  box-shadow: 10px 10px #031e36;
}

.actions {
  margin-top: 5px;
  margin-left: 20%;
  padding-bottom: 5px;
}

.actions button {
  width: 150px;
  height: 30px;
  color: #ffffff;
  background: #0f589d;
  margin-right: 5px;
  border-radius: 15px;
  text-align: center;
  padding-left: 3%;
}
.actions button:hover {
  color: #eaab28;
  border: 1px solid #EEEEEE;
  transition-duration: 0.5s;
}

.line {
  margin-left: 5px;
  display: inline-flex;
  font-size: 20px;
}
.line i {
  margin-right: 10px;
  height: 10px;
  font-size: 20px;
}

.popuptext {
  visibility: hidden;
  background:none;
  left: 50%;
  opacity: 0;
}
.popuptext.active{
  visibility: visible;
  opacity: 1;
  transition: 0.3s ease-in-out;
}

/* ************************************************************ <Transition> *************** */

.fade-enter-active, .fade-leave-active {
	transition: all 0.6s ease-in-out;
}
/* delay leave of parent element */
.fade-leave-active {
  transition-delay: 0.25s;
}

.fade-enter-from,
.fade-leave-to {
  transform: translateX(30px);
  opacity: 0;
}

/* we can also transition nested elements using nested selectors */
.fade-enter-active .section,
.fade-leave-active .section {
  transition: all 0.3s ease-in-out;
}
/* delay enter of nested element */
.fade-enter-active .section {
	transition-delay: 0.25s;
}

.fade-enter-from .section,
.fade-leave-to .section {
  transform: translateX(30px);
  opacity: 0.001;
}
</style>