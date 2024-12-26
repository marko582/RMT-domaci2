/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Windows HD
 */
public class Korisnik implements Serializable{
    private Long id;
    private String ime;
    private String prezime;
    private String jmbg;
    private String broj_pasosa;
    private String username;
    private String lozinka;

    public Korisnik() {
    }

    public Korisnik(String username, String lozinka) {
        this.username = username;
        this.lozinka = lozinka;
    }

    public Korisnik(String ime, String prezime, String jmbg, String broj_pasosa) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.broj_pasosa = broj_pasosa;
    }

    

    public Korisnik(String ime, String prezime, String jmbg, String broj_pasosa, String username, String lozinka) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.broj_pasosa = broj_pasosa;
        this.username = username;
        this.lozinka = lozinka;
    }
    
    

    public Korisnik(Long id, String ime, String prezime, String jmbg, String broj_pasosa, String username, String lozinka) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.broj_pasosa = broj_pasosa;
        this.username = username;
        this.lozinka = lozinka;
    }

      

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getBroj_pasosa() {
        return broj_pasosa;
    }

    public void setBroj_pasosa(String broj_pasosa) {
        this.broj_pasosa = broj_pasosa;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public String toString() {
        return "Korisnik{" + "ime=" + ime + ", prezime=" + prezime + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Korisnik other = (Korisnik) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.jmbg, other.jmbg)) {
            return false;
        }
        if (!Objects.equals(this.broj_pasosa, other.broj_pasosa)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.lozinka, other.lozinka)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
    
}
