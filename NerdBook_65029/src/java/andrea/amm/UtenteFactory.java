/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andrea.amm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea
 */
public class UtenteFactory
{

    private static UtenteFactory singleton;
    private String connectionString;

    public static UtenteFactory getInstance()
    {
        if (singleton == null)
        {
            singleton = new UtenteFactory();
        }
        return singleton;
    }

    private ArrayList<Utente> listaUtenti = new ArrayList<>();

    private UtenteFactory()
    {
       
    }

    public ArrayList<Utente> getList()
    {
        ArrayList<Utente> lista = new ArrayList<>();
        try
        {
            Connection conn = DriverManager.getConnection(connectionString, "utente", "utente");

            String sql = "select * from utenti";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet set = stmt.executeQuery();
            while (set.next())
            {
                Utente utente = new Utente();
                utente.setId(set.getInt("id"));
                utente.setNome(set.getString("nome"));
                utente.setCognome(set.getString("cognome"));
               // utente.setData(set.getString("dataDiNascita"));
                utente.setUrlFoto(set.getString("urlFoto"));
                utente.setFrase(set.getString("frase"));
                utente.setPassword(set.getString("password"));
                lista.add(utente);

            }
            stmt.close();
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(GruppoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public Utente getUtentebyId(int id)
    {
        Utente utente = null;
        try
        {
            Connection conn = DriverManager.getConnection(connectionString, "utente", "utente");

            String sql = "select * from utenti where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            if (set.next())
            {
                utente = new Utente();
                utente.setId(set.getInt("id"));
                utente.setNome(set.getString("nome"));
                utente.setCognome(set.getString("cognome"));
                utente.setUrlFoto(set.getString("urlFoto"));
                utente.setFrase(set.getString("frase"));
                utente.setPassword(set.getString("password"));

                stmt.close();
                conn.close();
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(GruppoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return utente;
    }
    

    public int getIdByNameAndPassword(String nome, String password)
    {
        int id = -1;
        try
        {
            Connection conn = DriverManager.getConnection(connectionString, "utente", "utente");

            String sql = "select id from utenti where nome = ? and password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, password);
            ResultSet set = stmt.executeQuery();
            if (set.next())
            {
                id = set.getInt("id");
                stmt.close();
                conn.close();
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(GruppoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void setConnectionString(String s)
    {
        this.connectionString = s;
    }

    public String getConnectionString()
    {
        return this.connectionString;
    }
    
    public void cancellaUtente(Utente utente)
    {
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(connectionString, "utente", "utente");
            conn.setAutoCommit(false);
            String sql1 = "delete from amici where id1 = ? or id2 = ?";
            PreparedStatement stmt = conn.prepareStatement(sql1);
            stmt.setInt(1, utente.getId());
            stmt.setInt(2, utente.getId());
            stmt.executeUpdate();

            String sql2 = "delete from membri where id2 = ?";
            stmt = conn.prepareStatement(sql2);
            stmt.setInt(1, utente.getId());
            stmt.executeUpdate();
            
            
            String sql3 = "delete from posts where autore = ? or bacheca_ut = ?";
            stmt = conn.prepareStatement(sql3);
            stmt.setInt(1, utente.getId());
            stmt.setInt(2, utente.getId());
            stmt.executeUpdate();

            String sql4 = "delete from utenti where id = ?";
            stmt = conn.prepareStatement(sql4);
            stmt.setInt(1, utente.getId());
            stmt.executeUpdate();

            conn.commit();
            conn.close();
            stmt.close();

        } catch (SQLException ex)
        {
            Logger.getLogger(GruppoFactory.class.getName()).log(Level.SEVERE, null, ex);
            
            try
            {
                if(conn!=null)
                {
                    conn.rollback();
                    conn.close();
                }
                
                
            }catch(SQLException ex2)
            {
                 Logger.getLogger(GruppoFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
