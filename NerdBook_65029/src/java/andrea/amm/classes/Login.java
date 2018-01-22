/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andrea.amm.classes;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import andrea.amm.GruppoFactory;
import andrea.amm.PostFactory;
import andrea.amm.Utente;
import andrea.amm.UtenteFactory;


/**
 *
 * @author Andrea
 */
@WebServlet(urlPatterns ={"/login.html"}, loadOnStartup = 0)
public class Login extends HttpServlet {
    
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb";
    
    @Override
    public void init()
    {
       String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_BUILD_PATH;
       try
       {
           Class.forName(JDBC_DRIVER);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
       }
       GruppoFactory.getInstance().setConnectionString(dbConnection);
       PostFactory.getInstance().setConnectionString(dbConnection);
       UtenteFactory.getInstance().setConnectionString(dbConnection);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        

        
        HttpSession session = request.getSession();
        
        
        if(request.getParameter("logout")!=null)
        {
            session.invalidate();
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        
        if(session.getAttribute("loggedIn") != null &&
            session.getAttribute("loggedIn").equals(true)) 
        {
            int loggedUserID = (int)session.getAttribute("loggedUserID");
            Utente utenteLoggato = UtenteFactory.getInstance().getUtentebyId(loggedUserID);
            if(utenteLoggato.getNome().equals("") || utenteLoggato.getCognome().equals("") ||
               utenteLoggato.getUrlFoto().equals("") || utenteLoggato.getFrase().equals(""))
            {
                 request.getRequestDispatcher("profilo.jsp").forward(request, response);
            }
            

            request.getRequestDispatcher("bacheca.jsp").forward(request, response); // modifica da html a jsp
            return;
        
        } 
        else
        {
            String nome = request.getParameter("usr");
            String password = request.getParameter("psw");
        
            
           if(nome != null && password != null) 
            {
               
                int loggedUserID = UtenteFactory.getInstance().getIdByNameAndPassword(nome, password);
                
                
                if(loggedUserID!=-1)
                {
                    session.setAttribute("listaUtenti", UtenteFactory.getInstance().getList());
                    session.setAttribute("listaGruppi", GruppoFactory.getInstance().getList());
                    session.setAttribute("logged", true);
                    session.setAttribute("loggedUserID", loggedUserID);
                    session.setAttribute("utenteLoggato", UtenteFactory.getInstance().getUtentebyId(loggedUserID));
                    
                    //request.getRequestDispatcher("bacheca.html").forward(request, response);
                   
                     Utente utenteLoggato = UtenteFactory.getInstance().getUtentebyId(loggedUserID);
                    
                    if (utenteLoggato.getNome().equals("") || utenteLoggato.getCognome().equals("")
                            || utenteLoggato.getUrlFoto().equals("") || utenteLoggato.getFrase().equals("")) {
                        request.getRequestDispatcher("profilo.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("bacheca.html").forward(request, response); //mi fa vedere la bacheca con tutti i post, secondo quanto implementato nella servlet 
                    }
                    
                    return;
                } else { 
                    
                    //ritorno al form del login 
                    request.setAttribute("loginError", true);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }
                
                
            }
        }
        
        
        request.getRequestDispatcher("login.jsp").forward(request, response);
       
    }
          

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
