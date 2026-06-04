package models;

import java.util.concurrent.atomic.AtomicInteger;

public class Plat {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private final int id;
    private String nom;
    private String description;
    private double prix;

    public Plat(String nom, String description, double prix) {
        this.id = COUNTER.getAndIncrement();
        this.nom = nom;
        this.description = description;
        this.prix = prix;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s (%.2f €)", id, nom, description, prix);
    }
}
