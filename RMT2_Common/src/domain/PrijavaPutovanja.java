/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Windows HD
 */
public class PrijavaPutovanja implements Serializable{
    private Long id;
    private LocalDate datumPrijave;
    private LocalDate datumUlaska;
    private LocalDate datumIzlaska;
    private Integer trajanje;
    private Korisnik korisnik;
    private NacinPrevoza nacinPrevoza;
    private List<StavkaPrijave> drzave;

    public PrijavaPutovanja() {
    }

    public PrijavaPutovanja(Long id, LocalDate datumPrijave, LocalDate datumUlaska, LocalDate datumIzlaska, Integer trajanje, Korisnik korisnik, NacinPrevoza nacinPrevoza) {
        this.id = id;
        this.datumPrijave = datumPrijave;
        this.datumUlaska = datumUlaska;
        this.datumIzlaska = datumIzlaska;
        this.trajanje = trajanje;
        this.korisnik = korisnik;
        this.nacinPrevoza = nacinPrevoza;
    }

    public PrijavaPutovanja(LocalDate datumPrijave, LocalDate datumUlaska, LocalDate datumIzlaska, Integer trajanje, Korisnik korisnik, NacinPrevoza nacinPrevoza, List<StavkaPrijave> drzave) {
        this.datumPrijave = datumPrijave;
        this.datumUlaska = datumUlaska;
        this.datumIzlaska = datumIzlaska;
        this.trajanje = trajanje;
        this.korisnik = korisnik;
        this.nacinPrevoza = nacinPrevoza;
        this.drzave = drzave;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatumPrijave() {
        return datumPrijave;
    }

    public void setDatumPrijave(LocalDate datumPrijave) {
        this.datumPrijave = datumPrijave;
    }

    public LocalDate getDatumUlaska() {
        return datumUlaska;
    }

    public void setDatumUlaska(LocalDate datumUlaska) {
        this.datumUlaska = datumUlaska;
    }

    public LocalDate getDatumIzlaska() {
        return datumIzlaska;
    }

    public void setDatumIzlaska(LocalDate datumIzlaska) {
        this.datumIzlaska = datumIzlaska;
    }

    public Integer getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(Integer trajanje) {
        this.trajanje = trajanje;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public NacinPrevoza getNacinPrevoza() {
        return nacinPrevoza;
    }

    public void setNacinPrevoza(NacinPrevoza nacinPrevoza) {
        this.nacinPrevoza = nacinPrevoza;
    }

    public List<StavkaPrijave> getDrzave() {
        return drzave;
    }

    public void setDrzave(List<StavkaPrijave> drzave) {
        this.drzave = drzave;
    }

    
    
    @Override
    public String toString() {
        return "PrijavaPutovanja{" + "id=" + id + ", datumPrijave=" + datumPrijave + ", datumUlaska=" + datumUlaska + ", datumIzlaska=" + datumIzlaska + ", trajanje=" + trajanje + ", korisnik=" + korisnik + ", nacinPrevoza=" + nacinPrevoza + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final PrijavaPutovanja other = (PrijavaPutovanja) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.datumPrijave, other.datumPrijave)) {
            return false;
        }
        if (!Objects.equals(this.datumUlaska, other.datumUlaska)) {
            return false;
        }
        if (!Objects.equals(this.datumIzlaska, other.datumIzlaska)) {
            return false;
        }
        if (!Objects.equals(this.trajanje, other.trajanje)) {
            return false;
        }
        if (!Objects.equals(this.korisnik, other.korisnik)) {
            return false;
        }
        return Objects.equals(this.nacinPrevoza, other.nacinPrevoza);
    }
    
    
    
}
