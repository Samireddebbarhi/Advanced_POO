package org.example;

public class CompteCourant extends CompteBancaire {
    private double decouvertAutorise;

    public CompteCourant(String numeroCcompte, double solde, String nomTitulaire, double decouvertAutorise) {
        super(numeroCcompte, solde, nomTitulaire);
        this.decouvertAutorise = decouvertAutorise;
    }

    @Override
    public void retrait(double montant) throws FondsInsuffisantsException {
        if (montant > this.solde + this.decouvertAutorise) {
            throw new FondsInsuffisantsException("Fonds insuffisants pour effectuer le retrait, même avec le découvert autorisé.");
        }
        this.solde -= montant;
        System.out.println("Retrait de " + montant + " effectué. Nouveau solde: " + this.solde);
    }
}
