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
public class StavkaPrijave implements Serializable{
    private Long id;
    private Long rb;
    private DrzavaEu drzava;

    public StavkaPrijave() {
    }

    public StavkaPrijave(Long id, Long rb, DrzavaEu drzava) {
        this.id = id;
        this.rb = rb;
        this.drzava = drzava;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRb() {
        return rb;
    }

    public void setRb(Long rb) {
        this.rb = rb;
    }

    public DrzavaEu getDrzava() {
        return drzava;
    }

    public void setDrzava(DrzavaEu drzava) {
        this.drzava = drzava;
    }

    @Override
    public String toString() {
        return drzava.toString();
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
        final StavkaPrijave other = (StavkaPrijave) obj;
//        if (!Objects.equals(this.id, other.id)) {
//            return false;
//        }
//        if (!Objects.equals(this.rb, other.rb)) {
//            return false;
//        }
        return Objects.equals(this.drzava, other.drzava);
    }
    
    
    
}
