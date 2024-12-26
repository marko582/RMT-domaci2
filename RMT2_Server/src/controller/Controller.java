/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import db.DatabaseConnection;
import domain.DrzavaEu;
import domain.Korisnik;
import domain.NacinPrevoza;
import domain.PrijavaPutovanja;
import domain.StavkaPrijave;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

/**
 *
 * @author Windows HD
 */
public class Controller {
    private static Connection conn = DatabaseConnection.getConnection();
    
    public static List<NacinPrevoza> getListNacinPrevoza() throws SQLException{
        List<NacinPrevoza> lista = new LinkedList<NacinPrevoza>();
        String query="SELECT * FROM nacinprevoza";
        Statement st=conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            NacinPrevoza n = new NacinPrevoza(rs.getLong("id"), rs.getString("naziv"));
            lista.add(n);
        }
        return lista;
    }
    public static List<DrzavaEu> getListDrzave() throws SQLException{
        List<DrzavaEu> lista = new LinkedList<DrzavaEu>();
        String query="SELECT * FROM drzavaeu";
        Statement st=conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            DrzavaEu d = new DrzavaEu(rs.getLong("id"), rs.getString("naziv"));
            lista.add(d);
        }
        return lista;
    }
    
    
    public static void createKorisnik(Korisnik korisnik) throws SQLException{
        String query="INSERT INTO korisnik (ime, prezime, jmbg, broj_pasosa, username, lozinka)" +
        "VALUES (?,?,?,?,?,?);";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, korisnik.getIme());
        ps.setString(2, korisnik.getPrezime());
        ps.setString(3, korisnik.getJmbg());
        ps.setString(4, korisnik.getBroj_pasosa());
        ps.setString(5, korisnik.getUsername());
        ps.setString(6, korisnik.getLozinka());
        ps.executeUpdate();
        ps.close();
    }

    public static Korisnik getKorisnik(Korisnik korisnik) throws SQLException {
        String query = "SELECT * FROM korisnik WHERE username=? AND lozinka=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, korisnik.getUsername());
        ps.setString(2, korisnik.getLozinka());
        ResultSet rs = ps.executeQuery();
        Korisnik k=null;
        if(rs.next()){
            k = new Korisnik(rs.getLong("id"), rs.getString("ime"), rs.getString("prezime"), 
            rs.getString("jmbg"), rs.getString("broj_pasosa"), rs.getString("username"), rs.getString("lozinka"));
        }
        return k;
    }
    
    public static List<PrijavaPutovanja> getListPrijavaPutovanja(Korisnik korisnik) throws SQLException{
        List<PrijavaPutovanja> lista = new LinkedList<>();
        String query="SELECT * FROM prijavaputovanja JOIN nacinprevoza "
            + "ON prijavaputovanja.nacinprevozaid=nacinprevoza.id WHERE korisnikid="+korisnik.getId()+
                " ORDER BY prijavaputovanja.id DESC";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            PrijavaPutovanja pp = new PrijavaPutovanja(rs.getLong("id"), 
                rs.getDate("datumprijave").toLocalDate(), rs.getDate("datumulaska").toLocalDate(), 
                rs.getDate("datumizlaska").toLocalDate(), rs.getInt("trajanje"), korisnik, 
                new NacinPrevoza(rs.getLong("nacinprevozaid"), rs.getString("naziv")));
            lista.add(pp);
        }
        return lista;
    }
    
    
    
    public static void createPrijava(PrijavaPutovanja pp) throws SQLException{
        String query = "INSERT INTO prijavaputovanja (datumprijave, datumulaska, datumizlaska, trajanje, korisnikid, nacinprevozaid) "
           + "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setDate(1, Date.valueOf(pp.getDatumPrijave()));
        ps.setDate(2, Date.valueOf(pp.getDatumUlaska()));
        ps.setDate(3, Date.valueOf(pp.getDatumIzlaska()));
        ps.setInt(4,Integer.parseInt(String.valueOf(ChronoUnit.DAYS.between(pp.getDatumUlaska(), pp.getDatumIzlaska()))));
        ps.setLong(5, pp.getKorisnik().getId());
        ps.setLong(6, pp.getNacinPrevoza().getId());
        ps.executeUpdate();
        ResultSet rs=ps.getGeneratedKeys();
        rs.next();
        Long id = rs.getLong(1);
        List<StavkaPrijave> stavke = pp.getDrzave();
        for(StavkaPrijave s : stavke){
            query="INSERT INTO stavkaprijave (id,rb,drzavaid) VALUES(?,?,?)";
            ps=conn.prepareStatement(query);
            ps.setLong(1, id);
            ps.setLong(2, s.getRb());
            ps.setLong(3, s.getDrzava().getId());
            ps.executeUpdate();
        }
        ps.close();
    }
    
    public static Long getKorisnikId(Korisnik k) throws SQLException{
        String query = "SELECT id FROM korisnik WHERE jmbg='"+k.getJmbg()+"'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        if(rs.next()){
            return rs.getLong(1);
        }
        return 0l;
    }
    
    public static void updatePrijava(PrijavaPutovanja pp) throws SQLException{
        String query = "UPDATE prijavaputovanja SET datumprijave = ?, datumulaska = ?, "
                + "datumizlaska = ?, trajanje = ?, korisnikid = ?, nacinprevozaid = ? "
                + "WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setDate(1, Date.valueOf(LocalDate.now()));
        ps.setDate(2, Date.valueOf(pp.getDatumUlaska()));
        ps.setDate(3, Date.valueOf(pp.getDatumIzlaska()));
        ps.setInt(4,Integer.parseInt(String.valueOf(ChronoUnit.DAYS.between(pp.getDatumUlaska(), pp.getDatumIzlaska()))));
        ps.setLong(5, pp.getKorisnik().getId());
        ps.setLong(6, pp.getNacinPrevoza().getId());
        ps.setLong(7, pp.getId());
        ps.executeUpdate();
        
        query = "DELETE FROM stavkaprijave WHERE id=?";
        ps=conn.prepareStatement(query);
        ps.setLong(1, pp.getId());
        ps.executeUpdate();
        
        List<StavkaPrijave> drzave = pp.getDrzave();
        for(StavkaPrijave d : drzave){
            query = "INSERT INTO stavkaprijave (id, rb, drzavaid) VALUES (?,?,?)";
            ps=conn.prepareStatement(query);
            ps.setLong(1, pp.getId());
            ps.setLong(2, d.getRb());
            ps.setLong(3, d.getDrzava().getId());
            ps.executeUpdate();
        }
        ps.close();
    }
    
    public static List<StavkaPrijave> getStavkePrijave(PrijavaPutovanja pp) throws SQLException{
        List<StavkaPrijave> lista = new LinkedList<StavkaPrijave>();
        String query = "SELECT * FROM stavkaprijave JOIN drzavaeu ON drzavaeu.id=stavkaprijave.drzavaid"
                + " WHERE stavkaprijave.id="+pp.getId();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            StavkaPrijave s = new StavkaPrijave(rs.getLong("stavkaprijave.id"), rs.getLong("rb"), 
                    new DrzavaEu(rs.getLong("drzavaid"), rs.getString("naziv")));
            lista.add(s);
        }
        return lista;
    }
    
    public static List<Korisnik> getListKorisnici() throws SQLException{
        List<Korisnik> lista = new LinkedList<Korisnik>();
        String query="SELECT * FROM korisnik";
        Statement st=conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Korisnik k = new Korisnik(rs.getLong("id"), rs.getString("ime"), rs.getString("prezime"), 
                rs.getString("jmbg"), rs.getString("broj_pasosa"), rs.getString("username"), 
                    rs.getString("lozinka"));
            lista.add(k);
        }
        return lista;
    }
    
    public static Long getDrzavaId(String naziv) throws SQLException{
        String query = "SELECT id FROM drzavaeu WHERE naziv='"+naziv+"'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        return rs.getLong(1);
    }
}
