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

public class GruppoFactory 
{
    private static GruppoFactory singleton;
    private String connectionString;
    
    public static GruppoFactory getInstance() 
    {
        if(singleton == null) {
            singleton = new GruppoFactory();
        }
        return singleton;
    }
    
    private ArrayList<Gruppo> listaGruppi = new ArrayList<>();

    private GruppoFactory()
    {
        
  /*      
        
        ArrayList<Integer> utenti2 = new ArrayList<>();
        utenti2.add(1);
        utenti2.add(2);
        Gruppo gruppo1 = new Gruppo();
        gruppo1.setId(1);
        gruppo1.setNome("Iphonisti");
        gruppo1.setUtenti(utenti2);
        
        ArrayList<Integer> utenti3 = new ArrayList<>();
        utenti3.add(0);
        utenti3.add(2);
        Gruppo gruppo2 = new Gruppo();
        gruppo2.setId(2);
        gruppo2.setNome("androidisti");
        gruppo2.setUtenti(utenti3);
        
        listaGruppi.add(gruppo1);
        listaGruppi.add(gruppo2);
        */
        
    }
    
    public ArrayList<Gruppo> getList()
    {
        ArrayList<Gruppo> lista = new ArrayList<>();
        try
        {
          Connection conn = DriverManager.getConnection(connectionString, "utente", "utente");
          
          String sql = "select * from gruppi";
          PreparedStatement stmt = conn.prepareStatement(sql);
          ResultSet set = stmt.executeQuery();
          while(set.next())
          {
             Gruppo gruppo = new Gruppo();
             gruppo.setId(set.getInt("id"));
             gruppo.setNome(set.getString("nome"));
             lista.add(gruppo);
             
           
          }
          
            stmt.close();
            conn.close();
        }catch(SQLException ex){
            Logger.getLogger(GruppoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public Gruppo getGruppobyId(int id)
    {   
        Gruppo gruppo = null;
        try
        {
          Connection conn = DriverManager.getConnection(connectionString, "utente", "utente");
          
          String sql = "select * from gruppi where id = ?";
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setInt(1, id);
          ResultSet set = stmt.executeQuery();
          if(set.next())
          {
             gruppo = new Gruppo();
             gruppo.setId(set.getInt("id"));
             gruppo.setNome(set.getString("nome"));
             
               
             stmt.close();
             conn.close();
          }
        }catch(SQLException ex){
            Logger.getLogger(GruppoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gruppo;
        
        /*
        for(Gruppo i : listaGruppi)
        {
            if( i.getId() == id)
                return i;
        }
        return null;
        */
    }
    public void setConnectionString(String s)
    {
	this.connectionString = s;
    }
    
    public String getConnectionString()
    {
	return this.connectionString;
    }
    
 
}