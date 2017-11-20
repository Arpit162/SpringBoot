Steps to deploy the web app:
--------------------------------------------------------------
1-clone or download the project from git hub.
2-Extract it to the desired folder.
3-Copy the path upto the .pom file.
4-Import it in your eclipse ide.
5-Build the maven project(use command: clean install -U) in your eclipse ide;
6-Find MainApp.java inside package com.demo and run it as Java Applications since it is developed using SpringBoot.
7-When the web application is started go to the browser and browse(localhost:8080), you will see the web application index.jsp page.
8-I have used H2 embeded database, to see database browse(localhost:8080/console).
(credenatials details is there in src/main/resources/appliaction.properties file.)

DB deatils:
-------------------------------------------
2 tabel is being used.
1-Person
2-Authorizations
*Authorizations tabel is a mapping for persons tabel.
*Schema query is there in schema.sql
*Data qurey is there in data.sql

Views:
-------------------------------------------
1-createUser.jsp
2-index.jsp
3-patient.jsp
4-doctor.jsp
5-pharmacist.jsp

Resource URI's:
----------------------------------------------
1-/createUser
2-/dashboard
3-/adduser
4-/userApproval
5-/userRequest
6-/logout

Design Pattern used:
-----------------------------------------------
Model View Controller

Test cases:
----------------------------------------------
Code coverage is done by Junit and Mockito test cases. 

