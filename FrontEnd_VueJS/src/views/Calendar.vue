<template>
    <div class="title">
    <h1>CALENDAR</h1>
    <h5>Seleziona un giorno per visualizzare le ripetizioni disponibili</h5>
    </div>
  <!--
  <button id="btn1" v-on:click="send" style="margin-left: 30%">ABILITA SESSION SENZA LOGIN</button>
  -->
  <div class="container-cal">
    <div class="calendar-container">
        <header class="calendar-header">
            <div class="calendar-navigation">
                <span id="calendar-prev" class="fa fa-angle-left" v-on:click="next(-1)">
                </span>
                  <div class="calendar-current-date" id="month">
                    {{months[current.mIndex]}} {{current.yy}}
                  </div>
              <span id ="calendar-next" class="fa fa-angle-right" v-on:click="next(1)"></span>
              <p>
                Today : {{new Date().getDate()}} {{months[new Date().getMonth()]}} {{new Date().getFullYear()}}
                <br>
              </p>
            </div>
        </header>
        <div class="calendar-body">
            <ul class="calendar-dates">
              <li v-on:click="getInfo(1)">1</li>
              <li v-on:click="getInfo(2)">2</li>
              <li v-on:click="getInfo(3)">3</li>
              <li v-on:click="getInfo(4)">4</li>
              <li v-on:click="getInfo(5)">5</li>
              <li v-on:click="getInfo(6)">6</li>
              <li v-on:click="getInfo(7)">7</li>
              <li v-on:click="getInfo(8)">8</li>
              <li v-on:click="getInfo(9)">9</li>
              <li v-on:click="getInfo(10)">10</li>
              <li v-on:click="getInfo(11)">11</li>
              <li v-on:click="getInfo(12)">12</li>
              <li v-on:click="getInfo(13)">13</li>
              <li v-on:click="getInfo(14)">14</li>
              <li v-on:click="getInfo(15)">15</li>
              <li v-on:click="getInfo(16)">16</li>
              <li v-on:click="getInfo(17)">17</li>
              <li v-on:click="getInfo(18)">18</li>
              <li v-on:click="getInfo(19)">19</li>
              <li v-on:click="getInfo(20)">20</li>
              <li v-on:click="getInfo(21)">21</li>
              <li v-on:click="getInfo(22)">22</li>
              <li v-on:click="getInfo(23)">23</li>
              <li v-on:click="getInfo(24)">24</li>
              <li v-on:click="getInfo(25)">25</li>
              <li v-on:click="getInfo(26)">26</li>
              <li v-on:click="getInfo(27)">27</li>
              <li v-on:click="getInfo(28)">28</li>
              <li v-on:click="getInfo(29)">29</li>
              <li v-on:click="getInfo(30)">30</li>
              <li v-on:click="getInfo(31)">31</li>
            </ul>
        </div>

    </div>
      <div class="aside">
          <h3>
            Giorno: {{date}} {{current.yy}} <br/>
          </h3>
          <h5>Ripetizioni disponibili:</h5>
            <div v-for="v in val">
              <event-calendar class="ev-cal" :day="this.fullDate" :hour="v.hour" :docente="v.docente" :corso="v.corso" :idRip="v.idRip" :idAss="v.idAssociazione"></event-calendar>
            </div>

        </div>
    </div>

</template>

<script>
import {ref} from "vue";
import eventCalendar from "@/components/EventCalendar.vue";
export default {
  name: "calendar",
  components :{
    eventCalendar
  },
  data: function () {
    return {
      val: [],
      url: 'DemoJac1/ReservationController',
      date: ref(null),
      fullDate: ref(null),
      months: [
        "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto","Settembre","Ottobre","Novembre","Dicembre"
      ],
      current: ref({
        mIndex: new Date().getMonth(),
        yy : new Date().getFullYear(),
      })
    }
  },
  mounted() { //carico i dati
  },

  methods: {
    next(value) {

    if (value === 1) {
      if (this.current.mIndex !== 11) {
        this.current.mIndex += 1;
      } else {
        this.current.mIndex = 0
        this.current.yy += 1
      }
      } else {
        if(this.current.mIndex !== 0) {
          this.current.mIndex -= 1;
        } else {
          this.current.mIndex = 11;
          this.current.yy -= 1;
        }
      }
    },
    getInfo(day) {

      var year = this.current.yy;
      let month = this.current.mIndex+1;
      let simple = year +"-"+month+"-"+day;
      console.log("Date "+ simple);

      var self = this;
        $.get(this.url, {datePicked: simple}, function (data) {

          console.log(data);
          self.val = data;
      })
      this.date = day+"-"+month;
      this.fullDate = year+"-"+month +"-"+day; //questa la passo alla component child per poi effettuare la chiamata alla servlet
      console.log("qui");

      },

    send() {

        var self = this;
        self.$root.session.login = true;
        self.$root.session.user = "Jacopo";
        alert("CIAO JACOPO, la session ora e' in stato : " + self.$root.session.login);
        console.log("User : <<" + self.$root.session.user + ">> hai fatto il login?");
      },
  }
}
</script>
<style>


.container-cal {
    padding-left: 20%;
    padding-bottom: 30%;
    display: flex;
}

.calendar-container {
    background: #f3f2f2;
    width: 120%;
    border-radius: 15px;
    border: 3px solid #000000;
    height: fit-content;
}

.calendar-container header {
    align-items: center;
    padding: 25px 30px 10px;
    justify-content: space-between;
    border-radius: 10px;
    background: linear-gradient(to top, #193f60, #2a78bd);

}

header .calendar-navigation {
    display: flex;
}

header .calendar-navigation span {
    height: 30px;
    width: 30px;
    margin: 1px 20px;
    border: 2px solid #093054;
    cursor: pointer;
    text-align: center;
    line-height: 28px;
    border-radius: 50%;
    color: #093054;
    background-color: #f6f6f6;
    font-size: 1.9rem;
}

.calendar-navigation span:last-child {
    margin-right: -10px;
}

header .calendar-navigation span:hover {
    background: #2479b2;
    color: white;
}

header .calendar-current-date {
    font-weight: 500;
    font-size: 1.45rem;
    width: 20%;
}

.calendar-body {
    padding: 50px;
}

.calendar-body ul {
    list-style: none;
    flex-wrap: wrap;
    display: flex;
    text-align: center;
}

.calendar-body .calendar-dates {
    margin-bottom: 20px;
}

.calendar-body li {
    width: calc(100% / 7);
    font-size: 1.07rem;
    color: #414141;
}

.calendar-body .calendar-weekdays li {
    cursor: default;
    font-weight: 500;
}

.calendar-dates li {
    margin-top: 30px;
    position: relative;
    cursor: pointer;
}

.calendar-dates li:hover {
    color: #e8ac27;
    font-weight: bold;
}

.calendar-dates li:active {
    background: #0b4170;
    border-radius: 50%;
}


/* *********** SEZIONE EVENTI LATERALE DX ************* */

.aside {
    margin: 5%;
    width: 40%;
    color: #ffffff;
    height: content-box;
    align-content: space-around;
    background: linear-gradient(10deg, #193f60, #2a78bd);
    border-radius: 10px;
    border : 2px solid #ffffff;
    padding: 20px;
    box-sizing: content-box;
    font-style: italic;
    font-family: "Dubai";
}


.aside h3 {
  font-size: 25px;
}

.ev-cal:hover {
  transition: all 0.3s ease-in-out;
  transform: translateX(10px);
}
</style>