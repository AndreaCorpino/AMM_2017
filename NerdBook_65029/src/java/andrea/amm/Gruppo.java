/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andrea.amm;

import java.util.ArrayList;

/**
 *
 * @author Andrea
 */


public class Gruppo {
    public int id;
    public String nomeGruppo;
    private ArrayList<Integer> utenti;
    
    public Gruppo()
    {
        id = 0;
        nomeGruppo = "";
        utenti = null;
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

    /**
     * @return the nome
     */
    public String getNomeGruppo() {
        return nomeGruppo;
    }

    /**
     * @param nomeGruppo the nome 
     */
    public void setNome(String nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }


    /**
     * @return the utenti
     */
    public ArrayList<Integer> getUtenti() {
        return utenti;
    }

    /**
     * @param utenti the utenti to set
     */
    public void setUtenti(ArrayList<Integer> utenti) {
        this.utenti = utenti;
    }
    
}
