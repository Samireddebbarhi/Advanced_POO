package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<CompteBancaire> comptes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initialiserComptes();

        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    effectuerDepot();
                    break;
                case 2:
                    effectuerRetrait();
                    break;
                case 3:
                    afficherSolde();
                    break;
                case 4:
                    effectuerTransfert();
                    break;
                case 5:
                    appliquerInterets();
                    break;
                case 6:
                    supprimerCompte();
                    break;
                case 7:
                    continuer = false;
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
        scanner.close();
    }

    private static void initialiserComptes() {
        comptes.add(new CompteCourant("CC001", 1000, "Samir", 500));
        comptes.add(new CompteEpargne("CE001", 2000, "EDD", 0.05));
        comptes.add(new CompteBancaire("CB001", 1500, "ZaidBenaalouch"));
    }

    private static void afficherMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Effectuer un dépôt");
        System.out.println("2. Effectuer un retrait");
        System.out.println("3. Afficher le solde");
        System.out.println("4. Effectuer un transfert");
        System.out.println("5. Appliquer les intérêts (Compte Épargne)");
        System.out.println("6. Supprimer un compte");
        System.out.println("7. Quitter");
        System.out.print("Choisissez une option: ");
    }

    private static void effectuerDepot() {
        System.out.print("Entrez le numéro du compte: ");
        String numeroCompte = scanner.nextLine();
        System.out.print("Entrez le montant du dépôt: ");
        double montant = scanner.nextDouble();
        scanner.nextLine();

        CompteBancaire compte = trouverCompte(numeroCompte);
        if (compte != null) {
            compte.depot(montant);
        } else {
            System.out.println("Compte non trouvé.");
        }
    }

    private static void effectuerRetrait() {
        System.out.print("Entrez le numéro du compte: ");
        String numeroCompte = scanner.nextLine();
        System.out.print("Entrez le montant du retrait: ");
        double montant = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        CompteBancaire compte = trouverCompte(numeroCompte);
        if (compte != null) {
            try {
                compte.retrait(montant);
            } catch (FondsInsuffisantsException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        } else {
            System.out.println("Compte non trouvé.");
        }
    }

    private static void afficherSolde() {
        System.out.print("Entrez le numéro du compte: ");
        String numeroCompte = scanner.nextLine();

        CompteBancaire compte = trouverCompte(numeroCompte);
        if (compte != null) {
            compte.afficherSolde();
        } else {
            System.out.println("Compte non trouvé.");
        }
    }

    private static void effectuerTransfert() {
        System.out.print("Entrez le numéro du compte source: ");
        String compteSource = scanner.nextLine();
        System.out.print("Entrez le numéro du compte destination: ");
        String compteDestination = scanner.nextLine();
        System.out.print("Entrez le montant du transfert: ");
        double montant = scanner.nextDouble();
        scanner.nextLine();

        try {
            CompteBancaire.transfert(compteSource, compteDestination, montant);
        } catch (FondsInsuffisantsException | CompteInexistantException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private static void appliquerInterets() {
        System.out.print("Entrez le numéro du compte épargne: ");
        String numeroCompte = scanner.nextLine();

        CompteBancaire compte = trouverCompte(numeroCompte);
        if (compte instanceof CompteEpargne) {
            ((CompteEpargne) compte).appliquerInterets();
        } else {
            System.out.println("Ce n'est pas un compte épargne ou le compte n'existe pas.");
        }
    }

    private static void supprimerCompte() {
        System.out.print("Entrez le numéro du compte à supprimer: ");
        String numeroCompte = scanner.nextLine();

        CompteBancaire compte = trouverCompte(numeroCompte);
        if (compte != null) {
            comptes.remove(compte);
            System.out.println("Compte supprimé avec succès.");
        } else {
            System.out.println("Compte non trouvé.");
        }
    }

    private static CompteBancaire trouverCompte(String numeroCompte) {
        return comptes.stream()
                .filter(c -> c.getNumeroCcompte().equals(numeroCompte))
                .findFirst()
                .orElse(null);
    }
}
