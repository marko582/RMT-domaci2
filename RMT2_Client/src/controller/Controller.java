/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.DrzavaEu;
import domain.Korisnik;
import domain.NacinPrevoza;
import domain.PrijavaPutovanja;
import domain.StavkaPrijave;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Windows HD
 */
public class Controller {
    private static Controller instance;
    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    private Controller() throws IOException {
        socket = new Socket("127.0.0.1", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }
    
    public static Controller getInstance() throws IOException{
        if(instance==null)
            instance=new Controller();
        return instance;
    }
    
    public Korisnik login(Korisnik korisnik) throws Exception{
        Request request = new Request(Operation.LOGIN, korisnik);
        sender.send(request);
        Response response =(Response) receiver.receive();
        if(response.getException()==null){
            return (Korisnik) response.getResult();
        }
        else{
            throw new Exception(response.getException().getMessage());
        }
    }
    
    public List<NacinPrevoza> getListNacinPrevoza() throws Exception{
        Request request = new Request(Operation.GET_NACIN_PREVOZA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<NacinPrevoza>) response.getResult();
        }
        throw response.getException();
        
    }
    public List<DrzavaEu> getListDrzave() throws Exception{
        Request request = new Request(Operation.GET_DRZAVE);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<DrzavaEu>) response.getResult();
        }
        throw response.getException();
    }
    
    public List<PrijavaPutovanja> getListPrijavaPutovanja(Korisnik korisnik) throws Exception{
        Request request = new Request(Operation.GET_SVE_PRIJAVE,korisnik);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<PrijavaPutovanja>) response.getResult();
        }
        throw response.getException();
    }
    
    public void createKorisnik(Korisnik korisnik) throws Exception{
        Request request = new Request(Operation.REGISTER, korisnik);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            korisnik.setId(((Korisnik)response.getResult()).getId());
        }else{
            throw response.getException();
        }
    }
    
    public void createPrijava(PrijavaPutovanja pp) throws Exception{
        Request request = new Request(Operation.PRIJAVA_PUTOVANJE, pp);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            pp.setId(((PrijavaPutovanja)response.getResult()).getId());
        }else{
            throw response.getException();
        }

    }
//    public void primiFajl(PrijavaPutovanja pp) throws Exception{
//        Request request = new Request(Operation.FAJL,pp);
//        sender.send(request);
//                        String saveFilePath = "downloaded_file.txt";
//                     InputStream inputStream = socket.getInputStream();
//             FileOutputStream fileOutputStream = new FileOutputStream(saveFilePath);
//        
//                    byte[] buffer = new byte[4096];
//            int bytesRead;
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                fileOutputStream.write(buffer, 0, bytesRead);
//            }
//             System.out.println("Fajl uspešno preuzet i sačuvan na: " + saveFilePath);
//    }
    public void primiFajl(PrijavaPutovanja pp) throws Exception{
        Request request = new Request(Operation.FAJL,pp);
        sender.send(request);
         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
     PrintWriter fileWriter = new PrintWriter(new FileWriter("prijava_preuzeta.txt"));

    String line;
    while ((line = in.readLine()) != null) {
        // Piši u fajl
        fileWriter.println(line);
        System.out.println(line); // Opcionalno, ispiši sadržaj na konzolu
    }
    Response response = (Response) receiver.receive();
    
    }

    
    public Long getKorisnikId(Korisnik k) throws Exception{
        Request request = new Request(Operation.GET_KORISNIK_ID, k);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (Long) response.getResult();
        }
        throw response.getException();
    }
    
    public void updatePrijava(PrijavaPutovanja pp) throws Exception{
        Request request = new Request(Operation.UPDATE_PRIJAVA, pp);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()!=null){
            throw response.getException();
        }else{
            pp=(PrijavaPutovanja) response.getResult();
        }
    }
    public List<StavkaPrijave> getStavkePrijave(PrijavaPutovanja pp) throws Exception{
        Request request = new Request(Operation.GET_STAVKE_PRIJAVE, pp);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<StavkaPrijave>) response.getResult();
        }
        throw response.getException();
    }
    public Long getDrzavaId(String naziv) throws Exception{
        Request request = new Request(Operation.GET_DRZAVA_ID, naziv);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (Long) response.getResult();
        }
        throw response.getException();
    }

    public List<Korisnik> getListKorisnici() throws Exception{
        Request request=new Request(Operation.GET_KORISNICI);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<Korisnik>) response.getResult();
        }
        throw response.getException();
    }
    
    public void ping() throws Exception {
        Request request = new Request(Operation.PING);
        sender.send(request);
        Response response = (Response) receiver.receive();
    }
    
}
