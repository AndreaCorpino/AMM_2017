/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andrea.amm;
import java.io.Serializable;
/**
 *
 * @author Andrea
 */
public class Utente implements Serializable
{
    private int id;
    private String nome;
    private String cognome;
    private String urlFoto;
   // private String data;
    private String frase;
    private String password;
    
   
    
    public Utente()
    {
       id = 0;
       nome = "";
       cognome = "";
       
       //data = "";
       urlFoto = "";
       frase = "";
       password = "";
       
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @param nome the nome 
     */
    public void setNome(String nome) {
        if (nome == null)
            this.nome = "";
        else 
            this.nome=nome;
    }

    /**
     * @return the cognome
     */
    public String getCognome() {
        return this.cognome;
    }

    /**
     * @param cognome the cognome 
     */
    public void setCognome(String cognome) {
          if (cognome == null)
            this.cognome = "";
        else 
            this.cognome=cognome;
    }

    /**
     * @return the urlFoto
     */
    public String getUrlFoto() {
        return this.urlFoto;
    }

    /**
     * @param urlFoto the urlFoto 
     */
    public void setUrlFoto(String urlFoto) {
        if (urlFoto == null)
            this.urlFoto = "";
        else 
            this.urlFoto=urlFoto;
    }

    /**
     * @return the frase
     */
    public String getFrase() {
        return frase;
    }

    /**
     * @param frase the frase 
     */
    public void setFrase(String frase) {
        if (frase == null)
            this.frase = "";
        else 
            this.frase=frase;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    

   
    
    @Override
    public boolean equals(Object object)
    {
        if(!(object instanceof Utente))
            return false;
        Utente temp = (Utente)object;
        return temp.getId() == this.getId();
    }

    /**
     * @return the data
     */
  /*  public String getData()
    {
        return data;
    }

    /**
     * @param data the data to set
     */
  /*  public void setData(String data)
    {
        this.data = data;
    }
    
    
   */ 
    
}
