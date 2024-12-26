/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.DrzavaEu;
import domain.Korisnik;
import domain.NacinPrevoza;
import domain.PrijavaPutovanja;
import domain.StavkaPrijave;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Windows HD
 */
public class ClientHandler extends Thread {

    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        sender = new Sender(socket);
        receiver = new Receiver(socket);
        while (true) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                Operation operation = request.getOperation();
                if (operation == Operation.GET_NACIN_PREVOZA) {
                    List<NacinPrevoza> prevozi = Controller.getListNacinPrevoza();
                    response.setResult(prevozi);
                }
                if (operation == Operation.GET_DRZAVE) {
                    List<DrzavaEu> drzave = Controller.getListDrzave();
                    response.setResult(drzave);
                }
                if (operation == Operation.REGISTER) {
                    Korisnik korisnik = (Korisnik) request.getArgument();
                    Controller.createKorisnik(korisnik);
                    response.setResult(korisnik);
                }
                if (operation == Operation.LOGIN) {
                    Korisnik korisnik = (Korisnik) request.getArgument();
                    korisnik = Controller.getKorisnik(korisnik);
                    if (korisnik == null) {
                        response.setException(new Exception("Korisnik ne postoji!"));
                    } else {
                        response.setResult(korisnik);
                    }
                }
                if (operation == Operation.GET_SVE_PRIJAVE) {
                    Korisnik korisnik = (Korisnik) request.getArgument();
                    List<PrijavaPutovanja> prijave = Controller.getListPrijavaPutovanja(korisnik);
                    response.setResult(prijave);
                }
                if (operation == Operation.PRIJAVA_PUTOVANJE) {
                    PrijavaPutovanja prijava = (PrijavaPutovanja) request.getArgument();
                    Controller.createPrijava(prijava);
                    response.setResult(prijava);
                }
                if (operation == Operation.GET_KORISNIK_ID) {
                    Korisnik k = (Korisnik) request.getArgument();
                    Long id = Controller.getKorisnikId(k);
                    if(id.equals(0l)){
                        response.setException(new Exception("Korisnik ne postoji!"));
                    }else{
                        response.setResult(id);
                    }
                }
                if (operation == Operation.UPDATE_PRIJAVA) {
                    PrijavaPutovanja pp = (PrijavaPutovanja) request.getArgument();
                    Controller.updatePrijava(pp);

                }
                if (operation == Operation.GET_STAVKE_PRIJAVE) {
                    List<StavkaPrijave> stavke = Controller.getStavkePrijave((PrijavaPutovanja) request.getArgument());
                    response.setResult(stavke);
                }

                if (operation == Operation.GET_DRZAVA_ID) {
                    String naziv = (String) request.getArgument();
                    Long id = Controller.getDrzavaId(naziv);
                    response.setResult(id);
                }
                if (operation == Operation.GET_KORISNICI) {
                    List<Korisnik> korisnici = Controller.getListKorisnici();
                    response.setResult(korisnici);
                }

                sender.send(response);
                if (operation == Operation.FAJL) {
                    PrijavaPutovanja prijava = (PrijavaPutovanja) request.getArgument();
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                        String prijavaFajl = "prijava_" + prijava.getKorisnik().getJmbg() + ".txt";

                        try (PrintWriter fileWriter = new PrintWriter(new FileWriter(prijavaFajl))) {
                            fileWriter.println("Ime i prezime: " + prijava.getKorisnik().getIme());
                            fileWriter.println("Zemlje odredišta: " + prijava.getDrzave());
                            fileWriter.println("JMBG: " + prijava.getKorisnik().getJmbg());
                            fileWriter.println("Broj pasoša: " + prijava.getKorisnik().getBroj_pasosa());
                            fileWriter.println("Datum ulaska u EU: " + prijava.getDatumUlaska());
                            fileWriter.println("Datum izlaska iz EU: " + prijava.getDatumIzlaska());
                            fileWriter.println("Način prevoza: " + prijava.getNacinPrevoza().getNaziv());
                            Integer brojDana = Integer.parseInt(String.valueOf(ChronoUnit.DAYS.between(prijava.getDatumUlaska(), prijava.getDatumIzlaska())));
                            fileWriter.println("Broj dana boravka: " + brojDana);

                            if (brojDana > 90) {
                                fileWriter.println("Status: Plaća prijavu.");
                            } else {
                                fileWriter.println("Status: Ne plaća prijavu.");
                            }
                        }

                        out.println("Prijava uspešno kreirana. Fajl je generisan: " + prijavaFajl);

                        try (BufferedReader fileReader = new BufferedReader(new FileReader(prijavaFajl))) {
                            String line;
                            while ((line = fileReader.readLine()) != null) {
                                out.println(line);
                            }
                        }
                    }
                }

            } catch (SocketException ex) {
//                    System.out.println("socket exception, clientHandler,klijent je ugasen");
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Greška u primljenim podacima.", ex);
            } catch (Exception ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
