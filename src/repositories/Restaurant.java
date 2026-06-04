package repositories;

import models.Client;
import models.Commande;
import models.Plat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Restaurant {
    private final List<Plat> menu;
    private final List<Client> clients;
    private final List<Commande> commandes;

    public Restaurant() {
        this.menu = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.commandes = new ArrayList<>();
    }

    public void ajouterPlat(Plat plat) {
        menu.add(plat);
    }

    public boolean modifierPlat(int id, String nouveauNom, String nouvelleDescription, double nouveauPrix) {
        Optional<Plat> opt = menu.stream().filter(plat -> plat.getId() == id).findFirst();
        if (opt.isPresent()) {
            Plat plat = opt.get();
            plat.setNom(nouveauNom);
            plat.setDescription(nouvelleDescription);
            plat.setPrix(nouveauPrix);
            return true;
        }
        return false;
    }

    public boolean supprimerPlat(int id) {
        return menu.removeIf(plat -> plat.getId() == id);
    }

    public List<Plat> getPlats() {
        return new ArrayList<>(menu);
    }

    public void ajouterClient(Client client) {
        clients.add(client);
    }

    public List<Client> getClients() {
        return new ArrayList<>(clients);
    }

    public Commande creerCommande(Client client) {
        Commande commande = new Commande(client);
        commandes.add(commande);
        return commande;
    }

    public List<Commande> getCommandes() {
        return new ArrayList<>(commandes);
    }

    public Optional<Plat> trouverPlatParId(int id) {
        return menu.stream().filter(plat -> plat.getId() == id).findFirst();
    }

    public Optional<Client> trouverClientParId(int id) {
        return clients.stream().filter(client -> client.getId() == id).findFirst();
    }

    public Optional<Commande> trouverCommandeParId(int id) {
        return commandes.stream().filter(cmd -> cmd.getId() == id).findFirst();
    }
}
