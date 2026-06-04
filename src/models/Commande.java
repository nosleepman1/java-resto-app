package models;

import exceptions.CommandeVideException;
import interfaces.Calculable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Commande implements Calculable {

    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private final int id;
    private final Client client;
    private final List<Plat> plats;

    public Commande(Client client) {
        this.id = COUNTER.getAndIncrement();
        this.client = client;
        this.plats = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public List<Plat> getPlats() {
        return plats;
    }

    public void ajouterPlat(Plat plat) {
        if (plat != null) {
            plats.add(plat);
        }
    }

    @Override
    public double calculerTotal() {
        double total = 0.0;
        for (Plat plat : plats) {
            total += plat.getPrix();
        }
        return total;
    }

    public void valider() throws CommandeVideException {
        if (plats.isEmpty()) {
            throw new CommandeVideException();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Commande [%d] pour %s\n", id, client.getNom()));
        builder.append("Plats :\n");
        for (Plat plat : plats) {
            builder.append("  - ").append(plat).append("\n");
        }
        builder.append(String.format("Montant total : %.2f €", calculerTotal()));
        return builder.toString();
    }
}
