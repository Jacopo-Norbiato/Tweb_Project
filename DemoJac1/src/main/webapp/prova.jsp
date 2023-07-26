
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Single Page Application using JavaScript</title>

      <style type="text/css">
      #form-box {
      position: absolute;
      left: 250px;
      top: 200px;
      background: linear-gradient(to right, #c78a7a, #8c1e08);
      width: 280px;
      padding: 10px;
      color: black;
      border: #0000cc 2px dashed;
      display: none;
      }
      </style>
        <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

	    <script>
      /* VEDERE SLIDE PER ESEMPI DI CONTENT CHE APPAIONO/SCOMPAIONO */
	    var pages = {
		    'home' : `Welcome to XYZ Company. This is our home page!<br/><br/><img src='library.jpg' style='width:500px;' style="background-color: #ce6752/>`,
		    'aboutus' : `Our About Us page<br/><br/><img src='library.jpg' style='width:600px;' style='top: 400px' style="background-color: #0f589d" />`,
		    'services' : `Our Services page<br/><br/><img src='library.jpg' style='width:500px;' style="background-color: #d2d9e1/>`,
		    'contact' : `Our Contact Us page<br/><br/><img src='library.jpg' style='width:500px;' style="background-color: #89d51f />`
	    };

	    function getPageContent(page){
		    var contentToReturn;
		    switch(page){
			    case 'home':
				    contentToReturn = pages.home;
				    break;
			    case 'aboutus':
				    contentToReturn = pages.aboutus;
				    break;
			    case 'services':
				    contentToReturn = pages.services;
				    break;
			    case 'contact':
				    contentToReturn = pages.contact;
				    break;
			    default:
                    contentToReturn = pages.home;
				    break;
		        }
		    document.getElementById('content').innerHTML = contentToReturn;
        }
	    </script>

    </head>

<body onload="getPageContent('home')">



    <div id="component">
        <aside class="sidebar"></aside>
        <div class="content"></div>

    </div>

	<div style="width:10%; float: left;background: #eee;height: 100%;">
		<ul>
			<li style="line-height: 50px;">
				<a href="#" onclick="getPageContent('home')">
					Home
				</a>
			</li>
			<li style="line-height: 50px;">
				<a href="#" onclick="getPageContent('aboutus')">
					About Us
				</a>
			</li>
			<li style="line-height: 50px;">
				<a href="#" onclick="check('form-box', 'inline')">
					Services
				</a>
			</li>
			<li style="line-height: 50px;">
				<a href="#" onclick="check('form-box', 'none')">
					Contact
				</a>
			</li>
		</ul>

	</div>
<div style="width: 90%;float: right; background: #999;color:#fff; height: 100%;font-size: 50px;"><p style="margin-left: 50px;" id="content"><p></div>
    <div id="form-box">
            <div class="button-box">
                <div id="btn">
                <button type="button" class="toggle-btn" onclick="loadLogin()">Log In</button>
                <button type="button" class="toggle-btn" onclick="loadRegister()">Register</button>
                </div>
            </div>
            <p>Benvenuto!
            <br>Entra nell'area personale per cominciare
            </p>

            <form id = "loginForm" class="input-group">
                <input type="text" class="input-field" size="15" id="username" required name="Username" placeholder="Username">
                <input type="password" class="input-field" size="15" id="pw" required name="Pword" placeholder="Password">
                <input class="button1" type="button" id="button1" value="Login" onclick="login();"/>

                <div id="LoginReport" class="outputTextArea" align="justify">
                    <script type="text/javascript">
                        var xhrObj = setXMLHttpRequest();
                </script>
            </div>
            </form>


            <form id = "registerForm" class="input-group">
                <input type="text" class="input-field" size="15" id="name" required name="Name" placeholder="Name">
                <input type="text" class="input-field" size="15" id="surname" required name="Surname" placeholder="Surname">
                <input type="password" class="input-field" size="15" id="pword" required name="Pword" placeholder="Password">
                <input class="button1" type="button" id="button2" value="Register" onclick="register();"/>
                <div id="RegisterReport" class="outputTextArea" align="justify">
                    <script type="text/javascript">
                        var xhrObj = setXMLHttpRequest();
                    </script>
                </div>
            </form>
        </div>
    <script type="text/javascript">

      /* nella sezione <style> ho definito la visibilità del div con id=from-box come display : none
      quindi quando viene invocata la funzione check()
      viene individuato il div con apposito id, quindi viene settata
      la visibility : inline
       */
    function check(id, visibility) {
      document.getElementById(id).style.display = visibility;
    }
    </script>

</body>

</html>