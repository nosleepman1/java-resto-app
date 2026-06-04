package models;

import java.util.concurrent.atomic.AtomicInteger;

public class Client {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private final int id;
    private String nom;
    private String telephone;

    public Client(String nom, String telephone) {
        this.id = COUNTER.getAndIncrement();
        this.nom = nom;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s", id, nom, telephone);
    }
}
