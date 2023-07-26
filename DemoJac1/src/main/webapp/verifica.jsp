<%--
  Created by IntelliJ IDEA.
  User: 39345
  Date: 04/07/2023
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="style.css">

</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<nav>
<h2>PROVA</h2>
<div id="verifica">CIAO, SEI IN Verifica.jsp</div>

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
    <h1>Docenti in formato json utilizzando vue.js</h1>
     <div id="app">
    <button v-on:click="getDocenti">Ottieni</button>
         <di v-for="docente in doc">
             <ul>({{docente.id}}) {{docente.nome}} {{docente.cognome}}</ul>
         </di>
    </div>

<script>
    var app = new Vue ({
        el: '#app',
        data: {
            doc: [],
            link: 'Verifica'
        },
        methods:{
            getDocenti: function(){
                var self = this;
                $.get(this.link, function(data) {
                    self.doc = data;
                    //self.coppie = JSON.stringify(data); // non funziona
                    });
            }
        }
    });
</script>
</body>
</html>
