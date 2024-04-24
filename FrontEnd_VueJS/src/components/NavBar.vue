<template onload="setUser">

  <div class="wrapper" v-on:click="setUser">
        <input type="checkbox" id="btn" hidden>
         <label for="btn" class="menu-btn">
             <i class="fas fa-bars" id="menu"></i>
             <i class="fas fa-times" id="cancel"></i>
         </label>
        <nav id="sidebar">
         <div class="title">Side Menu</div>

             <ul class="list-items">
               <div class="userSection">
               <div v-if="admin">
                 <i class="fa-solid fa-user-shield"></i>
                 <i class="detailsUser">{{username}} <br/>
                 {{roleDef}}</i>
               </div>
                <div v-else>
                  <i class="fa-solid fa-user-graduate"></i>
                  <i class="detailsUser">{{username}}<br/>
                    {{roleDef}}</i>
                </div>
               </div>

               <li><router-link to="/"><i class="fas fa-pencil"></i>     Login</router-link></li>
                  <li><router-link to="/agenda"><i class="fas fa fa-home"></i>     Agenda</router-link></li>
                  <li><router-link to="/about"><i class="fas fa-calendar-check" aria-hidden="true"></i>     Calendar</router-link></li>
                 <li><router-link to="/information"><i class="fa-solid fa-circle-info"></i>     Admin Information</router-link></li>
                 <li><router-link to="/edit"><i class="fa-solid fa-pen-clip"></i>     Admin Edit</router-link></li>
                 <li><router-link to="/logout"><i class="fas fa-sign-in"></i>     Log out</router-link></li>

             </ul>
        </nav>
    </div>
</template>

<script>
import {ref} from "vue";
export default {
  name: "NavBar",
  data() {
    return {
      admin : false,
      role: ref(0),
      roleDef : ref(""),
      username: ref("guest"),
    }
  },
  methods: {
    setUser() {
      if(this.role === 0) { //eseguo la chiamata al root solo la prima volta
        var self = this;
        if(self.$root.session.role === 2) {
          self.admin = true;
          self.username = self.$root.session.user;
          self.role = 2;
          self.roleDef = "Logged in as: Admin"
          //console.log("username admin : " + self.username);
        }
        if(self.$root.session.role === 1) {

            self.username = self.$root.session.user;
            self.role = 1;
            self.roleDef = "Logged in as: User"
            //console.log("username user : " + self.username);
          }
      }
      console.log("User non riconosciuto : guest");
    }
  }
}
</script>

<style scoped>

nav {
    top: 0;
    bottom: 0;
    left: 0;
    height: 100%;
    width: 50px;
    background-color: #7998af;
    box-shadow: 0 20px 25px rgba(0, 0, 0, 0.1);
    text-align: match-parent;
    font-size: 12px;
    float: left;
}
nav:hover {
    width: 110px;
    transition: all 0.5s ease;
}

.wrapper {
    height: 100%;
    width: 300px;
    position: relative;
}
.wrapper .menu-btn {
    position: fixed;
    left: 15px;
    top: 0px;
    background: #0f589d;
    color: #fff;
    height: 45px;
    width: 45px;
    z-index: 9999;
    border: 1px solid #333;
    border-radius: 5px;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: all 0.4s ease;
}

#btn:checked ~ .menu-btn {
    left: 247px;
}
.wrapper .menu-btn i {
    position: absolute;
    /* */
    transform: none;
    font-size: 23px;
    transition: all 0.4s ease;
}
.wrapper .menu-btn i.fa-times {
    opacity: 0;
}
#btn:checked ~ .menu-btn i.fa-times {
    opacity: 1;
    transform: rotate(-180deg);
}
#btn:checked ~ .menu-btn i.fa-bars {
    opacity: 0;
    transform: rotate(180deg);
}
#sidebar {
    position: fixed;
    background: #193f60;
    height: 100%;
    width: 270px;
    overflow: hidden;
    left: -270px;
    transition: all 0.4s ease;
}
#btn:checked ~ #sidebar{
    left: 0;
}
#sidebar .title {
    line-height: 65px;
    text-align: center;
    background: linear-gradient(to bottom, #3880b2, #0b3052);
    font-size: 25px;
    font-weight: 600;
    color: #f2f2f2;
    border-bottom: 1px solid #222;
}
#sidebar .list-items {
    position: relative;
    background: linear-gradient(to top, #11395d, #e1e8ef);
    width: 100%;
    height: 100%;
    list-style: none;
}
#sidebar .list-items li {
    padding-left: 40px;
    line-height: 50px;
    box-sizing: border-box;
    border-top: 1px solid rgba(255, 255, 255, 0.1);
    border-bottom: 1px solid #333;
    transition: all 0.4s ease;
}
#sidebar .list-items li:hover {
    border-top: 1px solid transparent;
    border-bottom: 1px solid transparent;
    box-shadow: 0 0px 10px 3px #09416b;
}
#sidebar .list-items li:first-child {
    border-top: none;
}
#sidebar .list-items li a {
    color: #09416b;
    text-decoration: none;
    font-size: 18px;
    font-weight: 500;
    height: 100%;
    width: 100%;
    display: block;
    padding-left: 15px;
    box-sizing: border-box;
}

.list-items .userSection {
  padding-left: 15%;
  padding-top: 7%;
  padding-bottom: 5%;
  color: #041d33;
  border-bottom: 3px solid #041d33;
  background: linear-gradient(135deg, #c4d0dc, #afd0ee);
  font-size: 40px;
}

.userSection .detailsUser {
  font-family: "Sitka Text";
  font-weight: bold;
  margin-left: 5%;
  font-size: 20px;
}
#sidebar ul li:hover a {
    padding-left: 20px;
}
#btn:checked ~ .form-box {
    margin-left: 250px;
}

</style>