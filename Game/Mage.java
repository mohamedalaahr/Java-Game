package Game;

/**
 * Classe représentant un Mage.
 */
public class Mage extends Personnage {

    /**
     * Constructeur de la classe Mage.
     * 
     * @param nom Le nom du mage.
     */
    public Mage(String nom) {
        super(nom);
        // Points de vie restent à 100 par défaut
    }

    /**
     * Calcule les dégâts d'une attaque normale du Mage.
     * 
     * @return Les points de dégâts.
     */
    @Override
    protected int calculerDegats() {
        return 8 + (niveau * 2); // Dégâts de base augmentant avec le niveau
    }

    /**
     * Utilise une compétence spéciale du Mage.
     * 
     * @param cible Le personnage ciblé par la compétence.
     */
    @Override
    public void utiliserCompetence(Personnage cible) {
        System.out.println(nom + " utilise une compétence spéciale : Boule de Feu sur " + cible.getNom());
        int degats = 25 + (niveau * 3); // Dégâts de la compétence spéciale
        System.out.println(nom + " inflige " + degats + " points de dégâts magiques.");
        cible.recevoirDegats(degats);
        gagnerExperience(25); // Gain d'expérience pour l'utilisation de la compétence
    }
}
