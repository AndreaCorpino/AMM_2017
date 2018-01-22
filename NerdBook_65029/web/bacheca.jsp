

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="style.css" media="screen">
<!DOCTYPE html>
<html>
    <head>  
        <title>Bacheca</title>
        <meta charset="UTF-8">
        <meta name="description" content="Bacheca NerdBook">
        <meta name="author" content="Andrea Corpino">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         
    </head>
    <body>
        <!-- aggiunto-->
        <c:set var="page" value="bacheca" scope="request"/>
        
        <!--Importa l'header della pagina-->
        <jsp:include page="header.jsp"/>

        <!--Importa la sezione a margine della pagina-->
        <%-- <jsp:include page="nav.jsp"/>  --%>
               
        <div id="page">
            
            <!-- aggiunto-->
            <c:if test="${logged}">
            
                <div id="frase">
                    
                    
                    <c:if test="${nome==null}">
                        <div>
                            <h2>${bachecaUtente.nome} ${bachecaUtente.cognome} </h2>
                           
                        </div>
                    </c:if>
                    
                    
                    <c:if test="${nome!=null}">
                        <div>
                            <h2>${nome} ${bachecaUtente.cognome} </h2>
                            
                        </div>
                    </c:if>
                    
                    <c:if test="${cognome!=null}">
                        <div>
                            <h2>${bachecaUtente.nome} ${cognome} </h2>
                            
                        </div>
                    </c:if>
               
                    <c:if test="${frase!=null}">
                                <p>${frase}</p>
                    </c:if>   
                                
                    <c:if test="${frase==null}">
                          <p>${bachecaUtente.frase}</p>
                    </c:if>    
                    
                    <%-- <div>
                        <h2>${utenteLoggato.nome} ${utenteLoggato.cognome} </h2>
                        <p>  ${utenteLoggato.frase}</p>
                    </div>
                    
                    
                     <c:if test="${frase!=null}">
                                <p>${frase}</p>
                            </c:if>   
                            <c:if test="${frase==null}">
                                <p>${utenteLoggato.frase}</p>
                            </c:if>    
                    --%>
                </div>
                
                <!-- aggiunto-->        
                <c:if test="${confermaPost!='5'}">    
                    
                    <!-- aggiunto-->
                    <div id="form_post">
                        <form action="bacheca.html?<c:if test="${utente!=null}">utente=${utente}</c:if><c:if test="${gruppo!=null}">gruppo=${gruppo}</c:if>" method="post"> <!-- metti get per evitare il refresh -->
                <!-- "bacheca.html? -->            
                            <div class="testo_input">
                                <input name="testo" type="text" placeholder="testo post">
                            </div>    

                            <div class="url_input">
                                <input  name="link" type="url" placeholder="inserisci url">
                            </div>  
                            
                            <div class="option">
                                <input type="radio" name="tipoAllegato" value="TESTO" checked>Text
                                <input type="radio" name="tipoAllegato" value="LINK">Link
                                <input type="radio" name="tipoAllegato" value="IMMAGINE">Image
                            </div>
                            
                            <div class="button">
                                
                                <button type="submit" name="confermaPost" value="5">  Carica </button>
                                
                                
                            </div>      
                           
                       </form>
                        
     
                    </div>    
                <!-- aggiunto-->
                </c:if>  

                                
               <c:if test="${confermaPost=='5'}">
                   
                   
                   
                   <div id="form">
                            <form action="bacheca.html?<c:if test="${utente!=null}">utente=${utente}</c:if><c:if test="${gruppo!=null}">gruppo=${gruppo}</c:if>" method="post">
                                    <input type="hidden" name="salvaAllegato" value="${contenutoAllegato}">
                                    <input type="hidden" name="salvaTesto" value="${testo}">
                                    <input type="hidden" name="salvaTipo" value="${tipoAllegato}">
                                    <c:if test="${utente!=null}">
                                        <h1>Hai scritto sulla bacheca di: ${bachecaUtente.nome} ${bachecaUtente.cognome}</h1>
                                    </c:if>

                                    <c:if test="${gruppo!=null}">
                                        <h1>Hai scritto nel gruppo: ${bachecaGruppo.nomeGruppo}</h1>
                                    </c:if>
                                        
                                    <h3> Il Posto è stato condiviso con successo! </h3>    
                                    <button type="submit" id="invia" name="invia" value="0"> Ok </button>
                            </form>
                    </div>            
                </c:if>  
                                
                <c:if test="${confermaPost=='0'}">
                            <c:if test="${utente!=null}">
                                <p>Hai scritto nella bacheca di ${bachecaUtente.nome} ${bachecaUtente.cognome}</p>
                            </c:if>
                            
                            <c:if test="${gruppo!=null}">
                                <p>Hai scritto nel gruppo: ${bachecaGruppo.nomeGruppo}</p>
                            </c:if>
                </c:if> 
                                
            <c:if test="${logged==null}">
                <h2> Utente non loggato </h2>
            </c:if>
                                    
                                
            
                            
        <div id="post">                        
                                
              <%-- aggiunto: bacheca utente loggato --%>              
              <c:forEach var="post" items="${posts}">  
                        <div class="post_created">
                            
                            <div id="autore">
                               
                                <img title="profilo" alt="fotoAutore" src="${post.autore.urlFoto}" width="100" height="100">  
                                            
                                <div id="nome">
                                       <h3>Autore: ${post.autore.nome} ${post.autore.cognome} </h3>
                                </div>
                            </div>
                                
                           
                           
                            <%--Ho fatto in modo che nonostante il post possa avere 3 tipi ben distinti (testo,link,immagine)
                                si possa in ogni caso inserire del testo --%> 
            
                            <div class="contenuto">
                                <p>
                                    
                                    
                                    
                                    <c:if test="${post.type=='TESTO'}">
                                        <p>Contenuto: ${post.contenuto}</p>
                                    </c:if>
                                    
                                    
                                    <c:if test="${post.type=='LINK'}">
                                      <p>Link: </p>  <a href="${post.allegato}">${post.allegato}</a>
                                    </c:if>
                                               
                                        
                                </p>
                                <c:if test="${post.type=='IMMAGINE'}">
                                    
                                        <img id="postImg" alt="Foto post utente" src="${post.allegato}" width="60" height="60">
                                    
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
              
      

            </c:if>
           
        </div>   
        
         <nav id="nav_menu">

            <div id="search_button" >
                <div >
                    <i></i>
                    <input type="search" id="search" placeholder="Search..." />
                </div>
            </div>


           
                  <div id="people_div">

                <div id="logo_people" >

                    <h2 >
                        Persone
                    </h2>

                </div>
                
                <c:forEach var="utente" items="${listaUtenti}">
                    <ul>
                    <li>
                        <a href="bacheca.html?utente=${utente.id}">${utente.nome} </a>
                    </li>
                    </ul>
                </c:forEach>                

                
            </div>

            <div id="groups_div">

                <div id="logo_groups_div" >

                    <h2 id="gruppi">
                        Gruppi
                    </h2>

                </div>

                 <c:forEach var="gruppo" items="${listaGruppi}">
                     <ul>
                     <li>
                        
                        <a href="bacheca.html?gruppo=${gruppo.id}">${gruppo.nomeGruppo}</a>
                        
                    </li>
                    </ul>
                </c:forEach>                 
                                
                                
                                
                  
                        
                      
                        

           


        </nav>     
                            
       
        
        
        
    </body>
</html>
                       
                        
   