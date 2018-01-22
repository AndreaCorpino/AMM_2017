<%-- 
    Document   : header
    Created on : Dec 18, 2017, 1:21:35 PM
    Author     : Andrea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="style.css" media="screen">

<!DOCTYPE html>
 <header >

            <!--nerdbook_div-->
            <div id="nerdbook_div" >

                <h1 id="nerdbook_h1" >

                    Nerdbook

                </h1>

            </div>

            <!--profil_div-->
            <div id="profil_div" >

                <a href="profilo.html" >Profilo</a>

            </div>

            <!--bacheca_div-->
            <div  id="bachecha_div" >


                     <a href="bacheca.html">Bacheca</a>

            </div>
            
            <!--user_div-->
            <div id="user_div">

                <p>${utenteLoggato.nome} ${utenteLoggato.cognome} </p>
                    
                     <a href="login.html?logout=1">Logout</a>
                  

            </div>



        </header>

        
