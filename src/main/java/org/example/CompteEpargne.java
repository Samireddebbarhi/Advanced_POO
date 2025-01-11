package org.example;

public class CompteEpargne extends CompteBancaire {
    private double tauxInteret;

    public CompteEpargne(String numeroCcompte, double solde, String nomTitulaire, double tauxInteret) {
        super(numeroCcompte, solde, nomTitulaire);
        this.tauxInteret = tauxInteret;
    }

    public void appliquerInterets() {
        double interets = this.solde * this.tauxInteret;
        this.solde += interets;
        System.out.println("Intérêts appliqués. Nouveau solde: " + this.solde);
    }
}
