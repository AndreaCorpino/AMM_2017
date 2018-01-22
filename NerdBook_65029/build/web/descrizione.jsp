<%-- 
    Document   : descrizione
    Created on : Dec 18, 2017, 11:05:42 AM
    Author     : Andrea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Descrizione NerdBook</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!--Aggiungo i metainformazioni relativi a questa pagina -->
        <meta name="author" content="Andrea Corpino">
        <meta name="description" content="Descrizione social network Nerdbook">
        <meta name="keywords" content="Social Network, NerdBook, solitudine, amici" >
        <!--Allego il file CSS-->
        <link rel="stylesheet" type="text/css" href="style.css" media="all">
    </head>
    
    <body>
        
        <!--header-->
        
        <header>
            
           
              <div id="logo_div" >
                    
                    
              </div>
                
            <div id="phrase_div">
                
                <h1 id="nerdbook_phrase" >Nerdbook</h1>
                
            </div>
            
            <div id="link_page" >
                
                <ul>


                    <li> <a id="login" href="login.jsp">
                            Login
                        </a>
                    </li>

                    <li>   <a id="profilo" href="profilo.jsp">
                            Profilo
                        </a>
                    </li>


                    <li>   <a id="bacheca" href="bacheca.jsp">
                            Bacheca
                        </a>
                    </li>
                    
                </ul>
                
            </div>
            
            
     
        
        </header>
        <!--Fine header-->
 
        
            
        
        <div id="image_div">
 
            <div>
                    <img  title="Mondo Nerd" alt="NerdBook" src="img/planetario.png"
                         width="537" height="195" >
                    
                </div>
            
            
        </div >    
        
        
        
        <div id="section">
            
            <div id="welcome_div">

                <p id="welcome_phrase" >
                    Nerdbook ti aiuta a rimanere connesso con il mondo dietro il tuo pc.
                </p>


            </div>

            <div>


                <div id="asked_question" >
                    <h1>
                        Domande Frequenti:
                    </h1>
                </div>
                <ul>

                    <li> <h2>A chi è rivolto Nerdbook?</h2>
                    </li>
                    <p> Sei solo? <br>
                        Il sabato sera preferisci guardarti per la terza volta Game of Thrones anziché uscire? <br>
                        Se in questo momento stai annuendo con la testa forse è meglio se ti iscrivi.</p>

                    <li>
                        <h2>Quanto costa iscriversi su NerdBook?</h2>
                    </li>
                    
                    <p> L'equipe di scienziati di Nerdbook sanno bene che circa il 99.7 % dei soli nel mondo è povero 
                        <br/> ed è per questo che NerdBook è saraà sempre gratis!
                    </p>

                </ul>
            </div>

             <div id="finale_div">
                
                
                <div class="phrase_login" >

                    <h3>Cosa aspetti? Iscriviti!</h3>

                </div>
                
            </div>
            
            
        </div>
        
        
           
            
        
        
    </body>
    
    
    
</html>
