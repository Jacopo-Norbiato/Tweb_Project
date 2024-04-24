<script setup>
import {ref} from 'vue';

const show = ref(true);
const awesome = ref(true)
</script>

<template>

  <div class="con">
    <event-details></event-details>
    <div>
          <button @click="awesome = !awesome">Toggle</button>
          <h1 v-if="awesome">Vue è fantastico!</h1>
          <h1 v-else>Oh no</h1>
        </div>
     <button @click="show = !show">Toggle</button>
    <Transition name="nested">
    <div v-if="show" class="outer">
      <div class="inner">
        Hello
      </div>
    </div>
  </Transition>

  </div>

  <!--
<loader></loader>
-->
</template>

<script>

import EventDetails from "@/components/EventDetails.vue";
export default {
  name: "Login",
  components: {
    EventDetails,
  },
  data() {
    return {
      buttonTrigger: false,
    }
  },
  methods: {
    TogglePopUp () {
        this.buttonTrigger = !this.buttonTrigger;
      }
  },


};

</script>

<style scoped>

.con {
    width: 65%;
    height: 80vh;
    margin: 6% auto;
    background: linear-gradient(to top, #5e3d03, #e3bc66);
    padding: 5px;
}

article {
    height: 100%;
    overflow: hidden;
    background: #7998af;
}

article p {
    margin: 45px;
    font-size: 15px;
    font-style: italic;
    color: #d2d9e1;
    float: right;
}


img {
    mix-blend-mode: multiply;
    margin-left: 10px;
    margin-top: 300px;
    height: 30px;
}


table, th, tr, td {
    border: 1px solid #193f60;
    accent-color: #4479f1;
    align-content: center;
    text-align: center;
    alignment: center;
}


/*
**********************************
 */

#popup  {
  background: transparent;
}

.popuptext {
  visibility: hidden;
  font-family: "Times New Roman";
  background:none;
  left: 50%;
  opacity: 0;
}
#popup:hover .popuptext{
  visibility: visible;
  opacity: 1;
  transition: 0.3s ease-in-out;
}

/*
**********************************
 */

.outer, .inner {
	background: #eee;
  padding: 30px;
  min-height: 100px;
}

.inner {
  background: #ccc;
}

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
.nested-enter-active .inner,
.nested-leave-active .inner {
  transition: all 0.3s ease-in-out;
}
/* delay enter of nested element */
.nested-enter-active .inner {
	transition-delay: 0.25s;
}

.nested-enter-from .inner,
.nested-leave-to .inner {
  transform: translateX(30px);
  /*
  	Hack around a Chrome 96 bug in handling nested opacity transitions.
    This is not needed in other browsers or Chrome 99+ where the bug
    has been fixed.
  */
  opacity: 0.001;
}

/* *****************************************************************************************************

 */

</style>