package Game;

/**
 * Classe représentant un Voleur.
 */
public class Voleur extends Personnage {

    /**
     * Constructeur de la classe Voleur.
     * 
     * @param nom Le nom du voleur.
     */
    public Voleur(String nom) {
        super(nom);
        // Points de vie restent à 100 par défaut
    }

    /**
     * Calcule les dégâts d'une attaque normale du Voleur.
     * 
     * @return Les points de dégâts.
     */
    @Override
    protected int calculerDegats() {
        return 10 + (niveau * 2); // Dégâts de base augmentant avec le niveau
    }

    /**
     * Utilise une compétence spéciale du Voleur.
     * 
     * @param cible Le personnage ciblé par la compétence.
     */
    @Override
    public void utiliserCompetence(Personnage cible) {
        System.out.println(nom + " utilise une compétence spéciale : Attaque Furtive sur " + cible.getNom());
        int degats = 18 + (niveau * 2); // Dégâts de la compétence spéciale
        System.out.println(nom + " inflige " + degats + " points de dégâts rapides.");
        cible.recevoirDegats(degats);
        gagnerExperience(18); // Gain d'expérience pour l'utilisation de la compétence
    }
}
