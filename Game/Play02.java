package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Classe principale pour exécuter le jeu.
 */
public class Play02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Création des personnages
        Personnage guerrier = new Guerrier("Thor");
        Personnage mage = new Mage("Gandalf");
        Personnage voleur = new Voleur("Loki");

        int choix;
        Personnage joueur = null;
        List<Personnage> adversaires = new ArrayList<>();

        // Choix du personnage par le joueur
        System.out.println("Choisissez votre personnage :");
        System.out.println("1. Guerrier");
        System.out.println("2. Mage");
        System.out.println("3. Voleur");

        while (true) {
            System.out.print("Entrez le numéro de votre choix : ");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                break;
            } else {
                System.out.println("Veuillez entrer un numéro valide.");
                scanner.next(); // Ignorer l'entrée invalide
            }
        }

        // Assignation du personnage choisi et des adversaires
        switch (choix) {
            case 1:
                joueur = guerrier;
                adversaires.add(mage);
                adversaires.add(voleur);
                break;
            case 2:
                joueur = mage;
                adversaires.add(guerrier);
                adversaires.add(voleur);
                break;
            case 3:
                joueur = voleur;
                adversaires.add(guerrier);
                adversaires.add(mage);
                break;
            default:
                System.out.println("Choix invalide. Le Guerrier est sélectionné par défaut.");
                joueur = guerrier;
                adversaires.add(mage);
                adversaires.add(voleur);
                break;
        }

        // Affichage de l'état initial des personnages
        System.out.println("\nDébut de la bataille !");
        afficherEtatPersonnages(joueur, adversaires);

        // Boucle principale de combat
        while (joueur.estVivant() && adversaires.stream().anyMatch(Personnage::estVivant)) {
            System.out.println("\n--- Tour de combat ---");
            System.out.println("Choisissez une action :");
            System.out.println("1. Attaquer un adversaire");
            System.out.println("2. Utiliser une compétence spéciale");
            System.out.println("3. Passer son tour");

            int action = 0;
            while (true) {
                System.out.print("Entrez le numéro de l'action : ");
                if (scanner.hasNextInt()) {
                    action = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Veuillez entrer un numéro valide.");
                    scanner.next(); // Ignorer l'entrée invalide
                }
            }

            switch (action) {
                case 1:
                    attaquerAdversaire(scanner, joueur, adversaires);
                    break;

                case 2:
                    utiliserCompetence(scanner, joueur, adversaires);
                    break;

                case 3:
                    System.out.println(joueur.getNom() + " passe son tour.");
                    break;

                default:
                    System.out.println("Action invalide.");
                    break;
            }

            // Actions des adversaires
            for (Personnage adversaire : adversaires) {
                if (adversaire.estVivant()) {
                    adversaire.attaquer(joueur);
                    if (!joueur.estVivant()) {
                        System.out.println(joueur.getNom() + " a été vaincu !");
                        break;
                    }
                }
            }

            // Mise à jour de la liste des adversaires (suppression des personnages vaincus)
            adversaires.removeIf(adversaire -> !adversaire.estVivant());

            // Affichage de l'état des personnages après chaque tour
            if (joueur.estVivant()) {
                afficherEtatPersonnages(joueur, adversaires);
            }
        }

        scanner.close();

        // Résultat de la bataille
        if (joueur.estVivant()) {
            System.out.println("La bataille est terminée ! " + joueur.getNom() + " a gagné !");
        } else {
            System.out.println("La bataille est terminée ! " + joueur.getNom() + " a perdu.");
        }
    }

    /**
     * Méthode pour afficher l'état actuel des personnages.
     * 
     * @param joueur       Le personnage contrôlé par le joueur.
     * @param adversaires  La liste des adversaires.
     */
    private static void afficherEtatPersonnages(Personnage joueur, List<Personnage> adversaires) {
        System.out.println("\n=== État des Personnages ===");
        System.out.println(joueur.getNom() + " (" + joueur.getClass().getSimpleName() + ") - PV : " + joueur.getPointsDeVie() + " - Niveau : " + joueur.getNiveau());
        for (Personnage adversaire : adversaires) {
            System.out.println(adversaire.getNom() + " (" + adversaire.getClass().getSimpleName() + ") - PV : " + adversaire.getPointsDeVie() + " - Niveau : " + adversaire.getNiveau());
        }
        System.out.println("============================\n");
    }

    /**
     * Méthode pour gérer l'attaque du joueur contre un adversaire.
     * 
     * @param scanner      L'objet Scanner pour lire les entrées utilisateur.
     * @param joueur       Le personnage contrôlé par le joueur.
     * @param adversaires  La liste des adversaires.
     */
    private static void attaquerAdversaire(Scanner scanner, Personnage joueur, List<Personnage> adversaires) {
        // Filtrer les adversaires vivants
        List<Personnage> ciblesDisponibles = adversaires.stream()
                .filter(Personnage::estVivant)
                .collect(Collectors.toList());

        if (ciblesDisponibles.isEmpty()) {
            System.out.println("Il n'y a aucun adversaire vivant à attaquer.");
            return;
        }

        System.out.println("Choisissez un adversaire à attaquer (entrez 0 pour attaquer tous les adversaires) :");
        for (int i = 0; i < ciblesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + ciblesDisponibles.get(i).getNom());
        }
        System.out.println("0. Attaquer tous les adversaires");

        int cible;
        while (true) {
            System.out.print("Entrez le numéro de l'adversaire : ");
            if (scanner.hasNextInt()) {
                cible = scanner.nextInt();
                if (cible >= 0 && cible <= ciblesDisponibles.size()) {
                    break;
                } else {
                    System.out.println("Numéro invalide.");
                }
            } else {
                System.out.println("Veuillez entrer un numéro valide.");
                scanner.next(); // Ignorer l'entrée invalide
            }
        }

        if (cible == 0) {
            for (Personnage adversaire : ciblesDisponibles) {
                joueur.attaquer(adversaire);
                joueur.gagnerExperience(10); // Gain d'expérience pour chaque attaque
            }
        } else {
            Personnage cibleAdversaire = ciblesDisponibles.get(cible - 1);
            joueur.attaquer(cibleAdversaire);
            joueur.gagnerExperience(10); // Gain d'expérience pour l'attaque
        }
    }

    /**
     * Méthode pour gérer l'utilisation de la compétence spéciale du joueur.
     * 
     * @param scanner      L'objet Scanner pour lire les entrées utilisateur.
     * @param joueur       Le personnage contrôlé par le joueur.
     * @param adversaires  La liste des adversaires.
     */
    private static void utiliserCompetence(Scanner scanner, Personnage joueur, List<Personnage> adversaires) {
        // Filtrer les adversaires vivants
        List<Personnage> ciblesDisponibles = adversaires.stream()
                .filter(Personnage::estVivant)
                .collect(Collectors.toList());

        if (ciblesDisponibles.isEmpty()) {
            System.out.println("Il n'y a aucun adversaire vivant pour utiliser une compétence spéciale.");
            return;
        }

        System.out.println("Choisissez un adversaire pour utiliser la compétence spéciale (entrez 0 pour cibler tous les adversaires) :");
        for (int i = 0; i < ciblesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + ciblesDisponibles.get(i).getNom());
        }
        System.out.println("0. Utiliser la compétence sur tous les adversaires");

        int cible;
        while (true) {
            System.out.print("Entrez le numéro de l'adversaire : ");
            if (scanner.hasNextInt()) {
                cible = scanner.nextInt();
                if (cible >= 0 && cible <= ciblesDisponibles.size()) {
                    break;
                } else {
                    System.out.println("Numéro invalide.");
                }
            } else {
                System.out.println("Veuillez entrer un numéro valide.");
                scanner.next(); // Ignorer l'entrée invalide
            }
        }

        if (cible == 0) {
            for (Personnage adversaire : ciblesDisponibles) {
                joueur.utiliserCompetence(adversaire);
                // L'expérience est ajoutée dans la méthode utiliserCompetence
            }
        } else {
            Personnage cibleAdversaire = ciblesDisponibles.get(cible - 1);
            joueur.utiliserCompetence(cibleAdversaire);
            // L'expérience est ajoutée dans la méthode utiliserCompetence
        }
    }
}
