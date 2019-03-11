package com.company;

import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class IMAPHandler {
    protected String email;
    protected String senha;
    protected Properties properties;
    protected Session session;
    protected Store store;
    protected Folder inbox;

    public IMAPHandler(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

    public void listar() throws Exception{
        this.readFolder();
        this.fetchMessegeDetail();
    }

    public void criarPasta(String nome){

    }

    public void setProperties(){
        this.properties = new Properties();

        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", "imap.gmail.com");
        properties.put("mail.imaps.port", "993");
    }

    public void createSession() throws Exception{
        this.session = Session.getDefaultInstance(this.properties, null);

        this.store = session.getStore("imaps");

        System.out.println("Connection initiated......");

        this.store.connect(this.email, this.senha);
        System.out.println("Connection is ready :)");
    }

    public void readFolder() throws Exception{
        this.inbox = this.store.getFolder("inbox");
        this.inbox.open(Folder.READ_ONLY);
    }

    public void fetchMessegeDetail() throws Exception{
        System.out.println("Total Messages in INBOX: " + this.inbox.getMessageCount());
        Message[] messages = inbox.getMessages();

        for (int i = 0, n = this.inbox.getMessageCount(); i < n; i++) {
            System.out.println("Mail Subject:- " + messages[i].getSubject());
            System.out.println("Mail From:- "    + messages[i].getFrom()[0]);
            System.out.println("Mail Content:- " + messages[i].getContent().toString());
            System.out.println("------------------------------------------------------------");
        }
    }

    public void close() throws Exception{
        this.inbox.close(true);
        this.store.close();
    }
}
