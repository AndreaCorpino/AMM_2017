<%-- 
    Document   : login
    Created on : Dec 18, 2017, 10:59:33 AM
    Author     : Andrea
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
         <!--Aggiungo i metainformazioni relativi a questa pagina -->
        <meta name="author" content="Andrea Corpino">
        <meta name="description" content="Login del Social Network NerdBook">
        <meta name="keywords" content="Social Network, NerdBook, solitudine, amici, login" >
        <!--Allego il file CSS-->
        <link rel="stylesheet" type="text/css" href="style.css" media="all">
        
    </head>
    
    <body>
       <c:set var="page" value="login" scope="request"/>
       
        <div id="NB_login" >
            
            
            <h1>
                Nerdbook
            </h1>
            
        </div>
        
        <div id="login_form">
            
      
            
             <form action="login.html" method="post">
                 
                <div class="user_login">
            
                
                    <label class="ll" for="usr">Username</label>
                   

                        <input type='text' id='usr' name="usr" value=""  />

                    
                </div>
            
            <div class="psw_login">
                
                    <label class="ll" for="psw">Password</label>
                    

                        <input type='password' id='psw' name="psw" value=""  />

                    
            </div>
            
            
            
             <div id="button_login">

                    

                 <button class="button" type="submit">  Invia </button> 

                    

                </div>
                 
            </form>
            

            
        </div>
        
       
       
       
       <c:if test="${loginError==true}">
           <div class="error">
               <h2> LOGIN NON VALIDO! </h2>
           </div>
        </c:if>
       
         
    </body>
</html>


