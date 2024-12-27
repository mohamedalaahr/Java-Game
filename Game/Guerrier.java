package Game;

/**
 * Classe représentant un Guerrier.
 */
public class Guerrier extends Personnage {

    /**
     * Constructeur de la classe Guerrier.
     * 
     * @param nom Le nom du guerrier.
     */
    public Guerrier(String nom) {
        super(nom);
        // Points de vie restent à 100 par défaut
    }

    /**
     * Calcule les dégâts d'une attaque normale du Guerrier.
     * 
     * @return Les points de dégâts.
     */
    @Override
    protected int calculerDegats() {
        return 20 + (niveau * 3); // Dégâts de base augmentant avec le niveau
    }

    /**
     * Utilise une compétence spéciale du Guerrier.
     * 
     * @param cible Le personnage ciblé par la compétence.
     */
    @Override
    public void utiliserCompetence(Personnage cible) {
        System.out.println(nom + " utilise une compétence spéciale : Coup Puissant sur " + cible.getNom());
        int degats = 35 + (niveau * 4); // Dégâts de la compétence spéciale
        System.out.println(nom + " inflige " + degats + " points de dégâts supplémentaires.");
        cible.recevoirDegats(degats);
        gagnerExperience(20); // Gain d'expérience pour l'utilisation de la compétence
    }
}
