To execute the WebApp it's requested using a TomcatServer to host the application and the MySQL Database.
The backend was built using JavaEE WebApp configuration on IntelliJ IDEA and the frontend
is developed with VueJS implementation. 

In order to launch the application: 

1) Frontend-side :
    - run the code of vue js with npm configutation to detect bugs 
    - execute the build with Mvn Configuration
    - move the generated files from the "FrontEnd_VueJS/dist" repository to "Backend_JavaEE/src/main/webapp"

3) Backend-side :
   - run the application with TomCast Host Local configuration
   - check the presence of deployement file.war which must have the same address of the frontend
   - check the address in the servlet calls inside the code
   - launch xampp
   - run the code
