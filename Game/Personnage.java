package Game;

/**
 * Classe abstraite représentant un personnage dans le jeu.
 */
public abstract class Personnage {
    protected String nom;          // Nom du personnage
    protected int pointsDeVie;    // Points de vie du personnage
    protected int niveau;         // Niveau du personnage
    protected int experience;     // Points d'expérience du personnage

    /**
     * Constructeur de la classe Personnage.
     * 
     * @param nom Le nom du personnage.
     */
    public Personnage(String nom) {
        this.nom = nom;
        this.pointsDeVie = 100; // Points de vie par défaut
        this.niveau = 1;        // Niveau initial
        this.experience = 0;    // Expérience initiale
    }

    /**
     * Retourne le nom du personnage.
     * 
     * @return Le nom du personnage.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne les points de vie du personnage.
     * 
     * @return Les points de vie.
     */
    public int getPointsDeVie() {
        return pointsDeVie;
    }

    /**
     * Retourne le niveau du personnage.
     * 
     * @return Le niveau.
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * Vérifie si le personnage est toujours vivant.
     * 
     * @return true si les points de vie sont supérieurs à 0, sinon false.
     */
    public boolean estVivant() {
        return pointsDeVie > 0;
    }

    /**
     * Méthode pour attaquer un autre personnage.
     * 
     * @param cible Le personnage ciblé par l'attaque.
     */
    public void attaquer(Personnage cible) {
        int degats = calculerDegats();
        System.out.println(this.nom + " attaque " + cible.getNom() + " et inflige " + degats + " points de dégâts.");
        cible.recevoirDegats(degats);
    }

    /**
     * Méthode pour recevoir des dégâts.
     * 
     * @param degats Les points de dégâts reçus.
     */
    protected void recevoirDegats(int degats) {
        pointsDeVie -= degats;
        if (pointsDeVie < 0) {
            pointsDeVie = 0;
        }
        System.out.println(nom + " a maintenant " + pointsDeVie + " points de vie.");
    }

    /**
     * Méthode abstraite pour calculer les dégâts infligés par une attaque normale.
     * 
     * @return Les points de dégâts.
     */
    protected abstract int calculerDegats();

    /**
     * Méthode pour gagner de l'expérience.
     * 
     * @param xp Les points d'expérience gagnés.
     */
    public void gagnerExperience(int xp) {
        experience += xp;
        System.out.println(nom + " gagne " + xp + " points d'expérience. Expérience totale : " + experience);
        if (experience >= niveau * 100) {
            niveauUp();
        }
    }

    /**
     * Méthode pour augmenter le niveau du personnage.
     */
    protected void niveauUp() {
        experience -= niveau * 100;
        niveau++;
        pointsDeVie += 10; // Augmentation des points de vie lors de la montée de niveau
        System.out.println(nom + " monte au niveau " + niveau + "! Points de vie actuels : " + pointsDeVie);
    }

    /**
     * Méthode abstraite pour utiliser une compétence spéciale.
     * 
     * @param cible Le personnage ciblé par la compétence.
     */
    public abstract void utiliserCompetence(Personnage cible);
}
