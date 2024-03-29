/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package andrea.amm.classes;


import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import andrea.amm.*;

/**
 *
 * @author 
 */
@WebServlet(urlPatterns =
{
    "/bacheca.html"
})
public class Bacheca extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        if (session.getAttribute("logged") == null)
        {
            //Utente non loggato
            request.getRequestDispatcher("bacheca.jsp").forward(request, response);
            return;
        }

        if (session.getAttribute("logged") != null)
        {
            

            if (request.getParameter("confermaPost") != null)
            {
                request.setAttribute("testo", request.getParameter("testo"));
                request.setAttribute("link", request.getParameter("link"));
               
                request.setAttribute("tipoAllegato", request.getParameter("tipoAllegato"));
                
                
               
                /*Caricamento post nella bacheca  */
                
                Post post = new Post();
                post.setAutore((Utente) session.getAttribute("utenteLoggato"));
                if (request.getParameter("utente") == null && request.getParameter("gruppo") == null)
                {
                    post.setBacheca_ut((Utente) session.getAttribute("utenteLoggato"));
                } else
                {
                    if (request.getParameter("gruppo") == null && request.getParameter("utente") != null && !request.getParameter("utente").equals(""))
                    {
                        Utente bachecaUtente = UtenteFactory.getInstance().getUtentebyId(Integer.parseInt(request.getParameter("utente")));
                        post.setBacheca_ut(bachecaUtente);
                    } else
                    {
                        Gruppo bachecaGruppo = GruppoFactory.getInstance().getGruppobyId(Integer.parseInt(request.getParameter("gruppo")));
                        post.setGruppo(bachecaGruppo);
                    }
                }
                
                post.setContenuto(request.getParameter("testo"));
                switch (request.getParameter("tipoAllegato"))
                {
                    case "TESTO":
                        post.setType(Post.Type.TESTO);
                        
                        break;
                    case "IMMAGINE":
                        post.setType(Post.Type.IMMAGINE);
                        
                        break;
                    case "LINK":
                        post.setType(Post.Type.LINK);
                        
                        break;
                }
                if (post.getType() != Post.Type.TESTO)
                {
                    post.setAllegato(request.getParameter("link"));
                }
                
                PostFactory.getInstance().savePost(post);
                request.setAttribute("confermaPost", "5");

            }
            
            
            
        }
                
               

        ArrayList<Post> posts = new ArrayList<>();
        if (request.getParameter("utente") == null && request.getParameter("gruppo") == null)
        {
            request.setAttribute("utente", session.getAttribute("loggedUserID"));
            Utente bachecaUtente = UtenteFactory.getInstance().getUtentebyId((int) request.getAttribute("utente"));
            request.setAttribute("bachecaUtente", bachecaUtente);
            posts = PostFactory.getInstance().getPostbyBacheca((Utente) session.getAttribute("utenteLoggato"));
        } else if (request.getParameter("gruppo") == null && request.getParameter("utente") != null && !request.getParameter("utente").equals(""))
        {
            int id = Integer.parseInt(request.getParameter("utente"));
            request.setAttribute("utente", id);
            Utente bachecaUtente = UtenteFactory.getInstance().getUtentebyId(Integer.parseInt(request.getParameter("utente")));
            request.setAttribute("bachecaUtente", bachecaUtente);
            posts = PostFactory.getInstance().getPostbyBacheca(UtenteFactory.getInstance().getUtentebyId(id));
        } else if (request.getParameter("gruppo") != null && !request.getParameter("gruppo").equals(""))
        {
            int id = Integer.parseInt(request.getParameter("gruppo"));
            request.setAttribute("gruppo", id);
            Gruppo bachecaGruppo = GruppoFactory.getInstance().getGruppobyId(Integer.parseInt(request.getParameter("gruppo")));
            request.setAttribute("bachecaGruppo", bachecaGruppo);
            posts = PostFactory.getInstance().getPostbyGruppo(GruppoFactory.getInstance().getGruppobyId(id));
        }
        
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("bacheca.jsp").forward(request, response); //html
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
        public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
