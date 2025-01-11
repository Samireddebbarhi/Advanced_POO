package org.example;

import java.util.HashMap;
import java.util.Map;

public class CompteBancaire {
    protected String numeroCcompte;
    protected double solde;
    protected String nomTitulaire;
    protected static Map<String, CompteBancaire> comptes = new HashMap<>();

    public String getNumeroCcompte() {
        return numeroCcompte;
    }

    public void setNumeroCcompte(String numeroCcompte) {
        this.numeroCcompte = numeroCcompte;
    }

    public CompteBancaire(String numeroCcompte, double solde, String nomTitulaire) {
        this.numeroCcompte = numeroCcompte;
        this.solde = solde;
        this.nomTitulaire = nomTitulaire;
        comptes.put(numeroCcompte, this);
    }

    public void depot(double montant) {
        this.solde += montant;
        System.out.println("Dépôt de " + montant + " effectué. Nouveau solde: " + this.solde);
    }

    public void retrait(double montant) throws FondsInsuffisantsException {
        if (montant > this.solde) {
            throw new FondsInsuffisantsException("Fonds insuffisants pour effectuer le retrait.");
        }
        this.solde -= montant;
        System.out.println("Retrait de " + montant + " effectué. Nouveau solde: " + this.solde);
    }

    public void afficherSolde() {
        System.out.println("Solde du compte " + this.numeroCcompte + ": " + this.solde);
    }

    public static void transfert(String compteSource, String compteDestination, double montant)
            throws FondsInsuffisantsException, CompteInexistantException {
        CompteBancaire source = comptes.get(compteSource);
        CompteBancaire destination = comptes.get(compteDestination);

        if (source == null || destination == null) {
            throw new CompteInexistantException("Un des comptes n'existe pas.");
        }

        source.retrait(montant);
        destination.depot(montant);
        System.out.println("Transfert de " + montant + " effectué de " + compteSource + " à " + compteDestination);
    }
}

