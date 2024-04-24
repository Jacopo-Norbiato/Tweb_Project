<template>

  <div v-if="admin">
    <div class="title">
    <h1>EDIT PAGE</h1>
    <h5>In questa sezione puoi prendere visione dei docenti e corsi registrati, <br/>
    aggiungere dei valori o modificarne degli altri. </h5>
  </div>

    <div class="buttons-gen-edit">
          <button class="but-edit-load" v-on:click="loadDocenti" style="background: #437094">
            Mostra Docenti
          </button>
          <button class="but-edit-load" v-on:click="loadCorsi" style="background: #2779c0">
            Mostra Corsi
          </button>
          <button class="but-edit-load" v-on:click="loadRipetizioni" style="background: #f8ad1d">
            Mostra Ripetizioni
          </button>
      </div>

    <div class="edit-container">


     <div class="docenti-corsi">

       <div class="doc-grid-edit">
         <div class="section-edit">
          <h2>DOCENTI</h2>
            <button class="edit-button" v-on:click="middleDocShow">Aggiungi<i class="fa fa-plus"></i>
            </button>
         </div>
         <h6 class="popup" v-if="showDocEditor">Attenzione! Cliccando il pulsante "Elimina" verranno cancellati anche tutti i dati associati al docente selezionato.</h6>

         <table class="list-edit" v-if="showDocEditor">
            <tr>
            <th>ID</th><th>NOMINATIVO</th><th>CORSI</th><th style="width: 10px">actions</th>
            </tr>
            <tr v-for="d in doc">
            <td>{{d.id}}</td>
            <td>{{d.nome}} {{d.cognome}}</td>
            <td>{{d.corso}}</td>
              <td>
                <button v-on:click="delDoc(id1 = d.id, d.corso)"><i class="fa fa-times"></i>Elimina</button>
            </td>
            </tr>
         </table>
    </div>
    <div class="middle">

      <div class="middle-edit" v-if="middleDoc">
        <button id="close-edit" v-on:click="middleDocShow"><i class="fa fa-times"></i></button>
        <h3>Compila i campi seguenti<br/>per inserire un nuovo docente</h3>
        <ul>
            <input type="number" id="docIdA" required placeholder="ID-Docente" style="height: 25px">
            <input type="text" id="docNameA" size="20" required placeholder="Nome" style="height: 25px">
            <input type="text" id="docSurnameA" size="20" required placeholder="Cognome" style="height: 25px">
            <select style="margin-left: 20%" id="docCorsoA" class="doc-cor-line">
              <option value = "" disabled selected>Corso</option>
              <option v-for="c in cor" :value="c.ID_materia">{{c.ID_materia}} - {{c.Materia}}</option>
            </select>

        </ul>
        <button v-on:click="addDocente">Inserisci Docente</button>
      </div>

      <div class="middle-edit" v-if="middleCor">
        <button id="close-edit" v-on:click="middleCorShow"><i class="fa fa-times"></i></button>
        <h3>Compila i campi seguenti<br/>per inserire un nuovo corso<br/> o modificarne uno esistente</h3>
        <ul>
          <input type="number" id="corIdB" style="height: 25px" required placeholder="ID-Corso">
          <input type="text" id="corNameB" size="20" required placeholder="Materia" style="height: 25px">
        </ul>
        <button v-on:click="this.addCorso">Inserisci Corso</button>
      </div>

    </div>

      <div class="corsi-grid-edit">
        <div class="section-edit">
          <h2>CORSI</h2>
          <button class="edit-button" v-on:click="middleCorShow">Aggiungi<i class="fa fa-plus"></i>
          </button>
         </div>
         <h6 class="popup" v-if="showCorEditor">Attenzione! Cliccando il pulsante "Elimina" verranno cancellati anche tutti i dati associati al corso.</h6>

        <table class="list-edit" v-if="showCorEditor">
            <tr>
              <th>ID</th><th>MATERIA</th><th style="width: 10px">actions</th>
            </tr>
            <tr v-for="c in cor">
            <td>{{c.ID_materia}}</td>
            <td>{{c.Materia}}</td>
              <td>
               <button v-on:click="delCor(c.ID_materia)"><i class="fa fa-times"></i>Elimina</button>
              </td>
            </tr>
        </table>
      </div>

    </div>
    <div class="ripetizioni-container">
      <div class="ripetizioni-grid-edit">
      <div class="section-edit">
          <h2>RIPETIZIONI</h2>
          <button class="edit-button" v-on:click="middleRipShow">Aggiungi<i class="fa fa-plus"></i>
          </button>
         </div>
      <table class="list-edit" v-if="showRipEditor">
          <tr>
          <th>GIORNO</th><th>ORA</th><th>DOCENTE</th><th>MATERIA</th><th style="width: 10px">actions</th>
          </tr>
          <tr v-for="r in rip">
            <td>{{r.giorno}}</td>
            <td>{{r.hour}}</td>
            <td>{{r.docente}}</td>
            <td>{{r.corso}}</td>
            <td>
              <button v-on:click="delRip(r.idRip, r.giorno, r.slot)"><i class="fa fa-times"></i>Elimina</button>
            </td>
            </tr>
      </table>
    </div>
      <div class="middle-edit" v-if="middleRip">
        <button id="close-edit" v-on:click="middleRipShow"><i class="fa fa-times"></i></button>
        <h3>Compila i campi seguenti<br/>per inserire un nuovo corso</h3>
        <ul>

          <div class="line-rip">
            <h6>Seleziona docente</h6>
            <select style="margin-left: 20%" id="docIdC">
            <option value = "" disabled selected>Docente</option>
            <option v-for="d in doc" :value = "d.id" >{{d.id}} - {{d.nome}}  {{d.cognome}}</option>
          </select>
          </div>

          <div class="line-rip">
            <h6>Seleziona corso</h6>
            <select style="margin-left: 20%" id="corIdC">
            <option value = "" disabled selected>Corso</option>
            <option v-for="c in cor" :value="c.ID_materia">{{c.ID_materia}} - {{c.Materia}}</option>
          </select>
          </div>

          <div class="line-rip">
            <h6>Data</h6>
            <input type="date" id="dateC" required class="dateSel">
          </div>
          <div class="line-rip">
            <h6>Orario</h6>
            <select id="slotC" style="margin-left: 20%">
            <option value="">Seleziona</option>
            <option value="1">15.00 - 16.00</option>
            <option value="2">16.00 - 17.00</option>
            <option value="3">17.00 - 18.00</option>
            <option value="4">18.00 - 19.00</option>
            </select>
          </div>
          </ul>
        <button v-on:click="addRip">Inserisci Ripetizione</button>
      </div>
    </div>

    </div>

  </div> <!-- <div v-if"admin"> -->

  <div class="not-info" v-else>
      <div class="title">
      <h1>EDIT PAGE</h1>

        <i class="fa-solid fa-circle-exclamation"></i>
        <h2>Errore d'accesso</h2>
        <h5>Spiacenti, questa sezione e' riservata ai soli amministratori.<br/>
        Accedi come amministratore per poter prendere visione di <br/>
        questa schermata.</h5>
      </div>
    </div>
</template>

<script>
export default {
  name: "Edit",
  data () {
    return{
      admin: false,
      url: 'DemoJac1/EditController',

      doc : [],
      cor : [],
      rip: [],

      middleDoc: false,
      middleCor: false,
      middleRip: false,

      showDocEditor: false,
      showCorEditor: false,
      showRipEditor: false,

      idD: "",
      idC: "",
      nomeD: "",
      cognomeD: "",
      nomeC: "",
      giorno:"",
      hour: "",
    }
  },
  mounted() {
    var self = this;
      if (self.$root.session.role === 2) {
        self.admin = true;
      } else {
        alert(" ERROR : per accedere a questa sezione servono i privilegi da Amministratore ");
      }
  },
  methods: {
    loadDocenti() {
      var self = this;
      if(self.showDocEditor===false) {
        $.get(this.url, {action: "getDocenti"}, function (data) {
        console.log(data);
        self.doc = data;
        self.showDocEditor = !self.showDocEditor;
      })
      } else {
        self.showDocEditor = !self.showDocEditor;
      }

    },
    loadCorsi() {
      var self = this;
      if(self.showCorEditor===false) {
        $.get(this.url, {action: "getCorsi"}, function (data) {
          console.log(data);
          self.cor = data;
          self.showCorEditor = !self.showCorEditor;
        })
      } else {
        self.showCorEditor = !self.showCorEditor;
      }

    },
    loadRipetizioni(){
      var self = this;
      if(self.showRipEditor===false) {
        $.get(this.url, {action: "getRipetizioni"}, function (data) {
        console.log(data);
        self.rip = data;
        self.showRipEditor = !self.showRipEditor;
      })
      } else {
        self.showRipEditor = !self.showRipEditor;
      }
    },
    middleDocShow() {
      this.middleDoc = !this.middleDoc;
      if(this.middleCor === true) {
        this.middleCor = ! this.middleCor;
      }
    },
    middleCorShow() {
      this.middleCor = !this.middleCor;
      if(this.middleDoc === true) {
        this.middleDoc = ! this.middleDoc;
      }
      $.get(this.url, {action: "getCorsi"}, function (data) {
          console.log(data);
          self.cor = data;
      })
    },
    middleRipShow() {

      /* carico i docenti e i corsi
      per facilitare l'input dell'user
      con menu' a tendina che contengono i valori disponibili
       */
      this.middleRip = !this.middleRip;

      var self = this;
      $.get(this.url, {action: "getDocenti"}, function (data) {
        console.log(data);
        self.doc = data;
      })
      $.get(this.url, {action: "getCorsi"}, function (data) {
          console.log(data);
          self.cor = data;
      })
    },

    addDocente() {
      let x = "";
      let a = "docente";
      this.idD = document.getElementById("docIdA").value;
      this.nomeD = document.getElementById("docNameA").value;
      this.cognomeD = document.getElementById("docSurnameA").value;
      this.idC = document.querySelector('#docCorsoA').value;
      $.post(this.url, {action: "ADD", value: a,idD: this.idD, name: this.nomeD, surname: this.cognomeD, idC: this.idC}, function (data) {
          console.log(data);
          x = data;
          alert(x);
          this.loadDocenti();
      })
    },
    addCorso() {
      let x = "";
      let a = "corso";
      this.idC = document.getElementById("corIdB").value;
      this.nomeC = document.getElementById("corNameB").value;
      console.log("Sono in addCorso()");
      $.post(this.url, {action: "ADD", value: a, idC: this.idC, materia: this.nomeC}, function (data) {
          console.log(data);
          x = data;
          alert(x);
          this.loadCorsi();
      })
    },
    addRip(){
      let x = "";
      let a = "rip";
      this.idD = document.querySelector('#docIdC').value;
      this.idC = document.querySelector('#corIdC').value;
      this.giorno = document.querySelector("#dateC").value;
      this.hour = document.querySelector('#slotC').value;

      console.log("Giorno passato da VUE : " + this.giorno);
      $.post(this.url, {action: "ADD", value: a, idD: this.idD, idC: this.idC, day: this.giorno, slot: this.hour}, function (data) {
          console.log(data);
          x = data;
          alert(x);
          this.loadRipetizioni();
      })
    },
    delDoc(id1, id2) {
      let x = "";
      let a = "docente";

      $.post(this.url, {action: "DELETE", value: a, idD: id1, idC: id2}, function (data) {
        console.log(data);
        x = data;
        alert(x);
      })
    },
    delCor(id1) {
      let x = "";
      let a = "corso";

      $.post(this.url, {action: "DELETE", value: a, Corso: id1}, function (data) {
        console.log(data);
        x = data;
        alert(x);
      })
    },
    delRip(id1, id2, day, slot) {
      let x = "";
      let a = "rip";

      console.log("Giorno passato da VUE : " + day);
      $.post(this.url, {action: "DELETE", value: a , idR: id1, time:slot, giornata: day}, function (data) {
        console.log(data);
        x = data;
        alert(x);
      })
    },
  }

}
</script>

<style scoped>


.edit-container {
  padding-bottom: 20%;
  margin-top: 25px;
  width: 85%;
  height: fit-content;
  margin-left: 8%;
}

.edit-container h2 {
  color: #ffffff;
  font-family: Bahnschrift;
  padding-top: 20px;
  font-size: 50px;
  padding-left: 15%;
}

.buttons-gen-edit{
  display: inline-block;
  padding-left: 25%;
  margin-top: 3%;
  display: flex;
  position: relative;
}
.buttons-gen-edit .but-edit-load {
    color: white;
    border-radius: 12px;
    border: 3px solid #EEEEEE;
    height: 50px;
    width: 15%;
    margin-right: 2%;
    font-size: 20px;
}
.but-edit-load:hover {
  border: 3px solid #07243f;
  border-radius: 30px;
  transition: 0.3s ease-in-out;
}
.section-edit {
  display: flex;
  width: 120%;
}
.edit-button {
  width: 20%;
  height: 30px;
  position: page;
  margin-left: 5%;
  margin-top: 5%;
  border-radius: 10px;
  font-size: 14px;
  font-weight: bold;
  border: 3px solid #193f60;

}
.edit-button i {
  padding-left: 10px;
}
.edit-button:hover {
  border: 3px solid white;
  border-radius: 20px;
  transition: ease-in-out;
  transition-duration: 0.3s;
}
.docenti-corsi {
  display: flex;
  height: content-box;
}
.docenti-corsi .edit-button{
  color: #193f60;
  background: #f8ad1d;
}
.ripetizioni-container {
  display: flex;
  height: content-box;
}

.ripetizioni-container .middle-edit {
  margin: 4%;
  padding-left: 2%;
  padding-right: 5px;
  padding-bottom: 5%;
  width: content-box;
  height: fit-content;
}

.middle-edit {
  font-family: Bahnschrift;
  color: #193f60;
  background: #a1cef5;
  border-radius: 10px;
  margin-top: 22%;
  margin-bottom: 10%;
  width: 280px;
  padding-left: 5%;
  padding-right: 5%;
  padding-bottom: 10%;
}

.middle-edit h3 {
  margin-bottom: 12%;
}

.middle-edit input {
  border-radius: 10px;
  margin-bottom: 5px;
  width: content-box;
  text-align: center;
  margin-left: 20%;
}

.middle-edit button {
    width: 85%;
    padding: 5px 10px;
    margin-top: 5%;
    margin-left: 10%;
    margin-right: 10%;
    color: white;
    cursor: pointer;
    display: block;
    background: linear-gradient(to right, #1e72be, #044673);
    outline: none;
    border-radius: 10px;
}

.middle-edit button:hover {

  background: #298acc;
  font-weight: bold;
  border: 3px solid #0b3052;
  border-radius: 30px;
}

#close-edit {
  margin-left: 96%;
  width: 12%;
  border-radius: 10px;
  border: 1px solid #0b3052;
  background: #ffffff;
  color: black;
}

#close-edit:hover {
  color: #0f589d;
  background: #ecc87e;
  border-radius: 30px;
  border: 3px solid #0b3052;
}
.doc-grid-edit {
  background: #437094;
  border-radius: 10px;
  margin: 1%;
  width: 40%;
  height: fit-content;
  padding: 2%;
}
.corsi-grid-edit {
  background: #2779c0;
  border-radius: 10px;
  margin: 1%;
  width: 40%;
  height: fit-content;
  padding: 2%;
}
.popup {
  margin-left: 0;
  margin-right: 10px;
  padding: 5px;
  color: white;
  background: #0b3052;
  text-align: center;
  border-radius: 10px;
  font-size: 13px;
  opacity: 1;
}

.ripetizioni-grid-edit {
  background: #f8ad1d;
  border-radius: 10px;
  width: 70%;
  padding: 3%;

}
.ripetizioni-grid-edit .edit-button {
  margin-top: 2%;
  color: #ffffff;
  background: #073f75;
}
.line-rip {
  display: flex;
  padding: 1%;
  margin-bottom: 8px;
}

.line-rip h6 {
  text-align: center;
  font-size: 15px;
  margin-left: 2%;
}
select, .dateSel{
  border-radius: 10px;
  width: 80%;
  padding-left: 10px;
  margin-right: 5%;
}

.doc-cor-line {
  width: 60%;
  height: 40%;
}

</style>