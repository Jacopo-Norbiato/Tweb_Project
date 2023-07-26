<%--
  Created by IntelliJ IDEA.
  User: 39345
  Date: 08/07/2023
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login prova</title>
    <link rel="stylesheet" href="style.css">

</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>



<form action="Login" method="get">
    <input id="xx" name="submit" type="button" value="Chiedi" />
</form>
<div id="dati" class="outputTextArea"></div>
</nav>
    <script type="text/javascript">
    $(document).ready(function () { // When the DOM of this page is constructed
        $("#xx").click(function () {// on click of xx button do ...
            $.get("Verifica", {}, // GET to HelloServlet (hello-servlet)!!
                function (data) {      // callBack function
                    var info = JSON.stringify(data);
                    //alert(info);
                    $("#dati").html(info); // Select element xx
                    //(by ID) and insert dynamic content (data) into it
                });
        });
    });
    </script>

<!--
<h1>Stato delle prenotazioni in formato json utilizzando vue.js</h1>
     <div id="app">
    <button v-on:click="getRip">Visualizza</button>
         <di v-for="prenot in p">
             <ul>{{prenot.materia}}; {{prenot.nome}} {{prenot.cognome}}; {{prenot.day}} {{prenot.slots}}</ul>
         </di>
    </div>

<script>
    var app = new Vue ({
        el: '#app',
        data: {
            p: [],
            link: 'Login'
        },
        mounted() {
            this.getRip()
        },
        methods:{
            getRip: function(){
                var self = this;
                $.get(this.link, function(data) {
                    self.p = data;
                    });
            }
        }
    });
</script>
-->

</body>
</html>
