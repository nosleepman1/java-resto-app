package app;

import exceptions.CommandeVideException;
import models.Client;
import models.Commande;
import models.Plat;
import repositories.Restaurant;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        boolean continuer = true;

        while (continuer) {
            afficherMenuPrincipal();
            int choix = lireEntier("Choisissez une option : ");
            switch (choix) {
                case 1 -> gererPlats(restaurant);
                case 2 -> gererClients(restaurant);
                case 3 -> gererCommandes(restaurant);
                case 4 -> afficherToutesCommandes(restaurant);
                case 0 -> {
                    System.out.println("Au revoir !");
                    continuer = false;
                }
                default -> System.out.println("Option invalide. Essayez encore.");
            }
        }
    }

    private static void afficherMenuPrincipal() {
        System.out.println("\n=== Gestion Restaurant ===");
        System.out.println("1. Gestion des plats");
        System.out.println("2. Gestion des clients");
        System.out.println("3. Gestion des commandes");
        System.out.println("4. Afficher toutes les commandes");
        System.out.println("0. Quitter");
    }

    private static void gererPlats(Restaurant restaurant) {
        System.out.println("\n--- Gestion des plats ---");
        System.out.println("1. Ajouter un plat");
        System.out.println("2. Modifier un plat");
        System.out.println("3. Supprimer un plat");
        System.out.println("4. Afficher tous les plats");
        System.out.println("0. Retour");
        int choix = lireEntier("Choix : ");

        switch (choix) {
            case 1 -> ajouterPlat(restaurant);
            case 2 -> modifierPlat(restaurant);
            case 3 -> supprimerPlat(restaurant);
            case 4 -> afficherPlats(restaurant.getPlats());
            case 0 -> {}
            default -> System.out.println("Option invalide.");
        }
    }

    private static void ajouterPlat(Restaurant restaurant) {
        System.out.print("Nom du plat : ");
        String nom = scanner.nextLine().trim();
        System.out.print("Description : ");
        String description = scanner.nextLine().trim();
        double prix = lireDouble("Prix : ");
        restaurant.ajouterPlat(new Plat(nom, description, prix));
        System.out.println("Plat ajouté avec succès.");
    }

    private static void modifierPlat(Restaurant restaurant) {
        int id = lireEntier("ID du plat à modifier : ");
        Optional<Plat> platOpt = restaurant.trouverPlatParId(id);
        if (platOpt.isEmpty()) {
            System.out.println("Plat introuvable.");
            return;
        }
        Plat plat = platOpt.get();
        System.out.print("Nouveau nom (" + plat.getNom() + ") : ");
        String nom = scanner.nextLine().trim();
        if (nom.isEmpty()) {
            nom = plat.getNom();
        }
        System.out.print("Nouvelle description (" + plat.getDescription() + ") : ");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) {
            description = plat.getDescription();
        }
        System.out.print("Nouveau prix (" + plat.getPrix() + ") : ");
        String prixTexte = scanner.nextLine().trim();
        double prix = prixTexte.isEmpty() ? plat.getPrix() : Double.parseDouble(prixTexte);
        restaurant.modifierPlat(id, nom, description, prix);
        System.out.println("Plat modifié.");
    }

    private static void supprimerPlat(Restaurant restaurant) {
        int id = lireEntier("ID du plat à supprimer : ");
        if (restaurant.supprimerPlat(id)) {
            System.out.println("Plat supprimé.");
        } else {
            System.out.println("Plat introuvable.");
        }
    }

    private static void afficherPlats(List<Plat> plats) {
        System.out.println("\nListe des plats :");
        if (plats.isEmpty()) {
            System.out.println("Aucun plat disponible.");
            return;
        }
        for (Plat plat : plats) {
            System.out.println(plat);
        }
    }

    private static void gererClients(Restaurant restaurant) {
        System.out.println("\n--- Gestion des clients ---");
        System.out.println("1. Ajouter un client");
        System.out.println("2. Afficher les clients");
        System.out.println("0. Retour");
        int choix = lireEntier("Choix : ");

        switch (choix) {
            case 1 -> ajouterClient(restaurant);
            case 2 -> afficherClients(restaurant.getClients());
            case 0 -> {}
            default -> System.out.println("Option invalide.");
        }
    }

    private static void ajouterClient(Restaurant restaurant) {
        System.out.print("Nom du client : ");
        String nom = scanner.nextLine().trim();
        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine().trim();
        restaurant.ajouterClient(new Client(nom, telephone));
        System.out.println("Client ajouté.");
    }

    private static void afficherClients(List<Client> clients) {
        System.out.println("\nListe des clients :");
        if (clients.isEmpty()) {
            System.out.println("Aucun client enregistré.");
            return;
        }
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    private static void gererCommandes(Restaurant restaurant) {
        System.out.println("\n--- Gestion des commandes ---");
        System.out.println("1. Créer une commande");
        System.out.println("2. Ajouter un plat à une commande");
        System.out.println("3. Afficher le détail d'une commande");
        System.out.println("0. Retour");
        int choix = lireEntier("Choix : ");

        switch (choix) {
            case 1 -> creerCommande(restaurant);
            case 2 -> ajouterPlatCommande(restaurant);
            case 3 -> afficherDetailCommande(restaurant);
            case 0 -> {}
            default -> System.out.println("Option invalide.");
        }
    }

    private static void creerCommande(Restaurant restaurant) {
        int clientId = lireEntier("ID du client : ");
        Optional<Client> clientOpt = restaurant.trouverClientParId(clientId);
        if (clientOpt.isEmpty()) {
            System.out.println("Client introuvable.");
            return;
        }
        Commande commande = restaurant.creerCommande(clientOpt.get());
        System.out.println("Commande créée avec l'ID : " + commande.getId());
    }

    private static void ajouterPlatCommande(Restaurant restaurant) {
        int commandeId = lireEntier("ID de la commande : ");
        Optional<Commande> commandeOpt = restaurant.trouverCommandeParId(commandeId);
        if (commandeOpt.isEmpty()) {
            System.out.println("Commande introuvable.");
            return;
        }
        afficherPlats(restaurant.getPlats());
        int platId = lireEntier("ID du plat à ajouter : ");
        Optional<Plat> platOpt = restaurant.trouverPlatParId(platId);
        if (platOpt.isEmpty()) {
            System.out.println("Plat introuvable.");
            return;
        }
        commandeOpt.get().ajouterPlat(platOpt.get());
        System.out.println("Plat ajouté à la commande.");
    }

    private static void afficherDetailCommande(Restaurant restaurant) {
        int commandeId = lireEntier("ID de la commande : ");
        Optional<Commande> commandeOpt = restaurant.trouverCommandeParId(commandeId);
        if (commandeOpt.isEmpty()) {
            System.out.println("Commande introuvable.");
            return;
        }
        Commande commande = commandeOpt.get();
        try {
            commande.valider();
            System.out.println(commande);
        } catch (CommandeVideException e) {
            System.out.println("Commande invalide : " + e.getMessage());
        }
    }

    private static void afficherToutesCommandes(Restaurant restaurant) {
        System.out.println("\nToutes les commandes :");
        List<Commande> commandes = restaurant.getCommandes();
        if (commandes.isEmpty()) {
            System.out.println("Aucune commande enregistrée.");
            return;
        }
        for (Commande commande : commandes) {
            System.out.println(commande);
            System.out.println("-------------------------");
        }
    }

    private static int lireEntier(String message) {
        while (true) {
            System.out.print(message);
            try {
                int valeur = Integer.parseInt(scanner.nextLine().trim());
                return valeur;
            } catch (NumberFormatException e) {
                System.out.println("Veuillez saisir un nombre entier valide.");
            }
        }
    }

    private static double lireDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez saisir un nombre valide.");
            }
        }
    }
}
