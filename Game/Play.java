package Game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Play {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // إنشاء الشخصيات
        Personnage guerrier = new Guerrier("Thor");
        Personnage mage = new Mage("Gandalf");
        Personnage voleur = new Voleur("Loki");

        // إضافة الشخصيات إلى قائمة
        List<Personnage> personnages = new ArrayList<>();
        personnages.add(guerrier);
        personnages.add(mage);
        personnages.add(voleur);

        // عرض النقاط الأولية
        System.out.println("Début de la bataille !");
        afficherEtatPersonnages(personnages);

        // استمرار المعركة حتى يموت شخصيتان
        while (compterMorts(personnages) < 2) {
            System.out.println("\n--- Tour de combat ---");
            System.out.println("Choisissez deux personnages pour se battre :");

            // عرض الشخصيات الحية مع أرقام لاختيارها
            List<Personnage> vivants = getVivants(personnages);
            if (vivants.size() < 2) {
                break; // لا يمكن إتمام قتال إذا تبقت شخصية واحدة أو أقل
            }

            afficherOptions(personnages);

            // اختيار المقاتل الأول
            System.out.print("Entrez le numéro du premier combattant : ");
            int choix1 = scanner.nextInt() - 1;
            if (choix1 < 0 || choix1 >= personnages.size() || !personnages.get(choix1).estVivant()) {
                System.out.println("Choix invalide ou personnage déjà mort.");
                continue;
            }
            Personnage combattant1 = personnages.get(choix1);

            


            // اختيار المقاتل الثاني
            System.out.print("Entrez le numéro du deuxième combattant : ");
            int choix2 = scanner.nextInt() - 1;
            if (choix2 < 0 || choix2 >= personnages.size() || !personnages.get(choix2).estVivant() || choix2 == choix1) {
                System.out.println("Choix invalide أو نفس الشخصية المختارة مرتين.");
                continue;
            }
            Personnage combattant2 = personnages.get(choix2);


            
            

            // بدء القتال بين المقاتلين
            System.out.println("\n" + combattant1.getNom() + " ("
                    + combattant1.getClass().getSimpleName() + ") contre "
                    + combattant2.getNom() + " ("
                    + combattant2.getClass().getSimpleName() + ") !");
            combattant1.attaquer(combattant2);
            if (combattant2.estVivant()) {
                combattant2.attaquer(combattant1);
            }

            // التحقق مما إذا مات أحد المقاتلين
            if (!combattant1.estVivant()) {
                System.out.println(combattant1.getNom() + " est vaincu !");
            }
            if (!combattant2.estVivant()) {
                System.out.println(combattant2.getNom() + " est vaincu !");
            }

            // عرض حالة الشخصيات بعد الجولة
            afficherEtatPersonnages(personnages);
        }

        // إعلان الفائز
        declarerVainqueur(personnages);

        System.out.println("Le combat est terminé !");
        scanner.close();
    }

    /**
     * يعرض حالة جميع الشخصيات.
     */
    private static void afficherEtatPersonnages(List<Personnage> personnages) {
        System.out.println("\nÉtat des personnages :");
        for (int i = 0; i < personnages.size(); i++) {
            Personnage p = personnages.get(i);
            String statut = p.estVivant() ? "Vivant" : "Mort";
            System.out.println((i + 1) + ". " + p.getNom() + " (" + p.getClass().getSimpleName() + ") - PV: " 
                + p.getPointsDeVie() + " - " + statut);
        }
    }

    /**
     * يعرض خيارات الشخصيات للحرب.
     */
    private static void afficherOptions(List<Personnage> personnages) {
        for (int i = 0; i < personnages.size(); i++) {
            Personnage p = personnages.get(i);
            String statut = p.estVivant() ? "Vivant" : "Mort";
            System.out.println((i + 1) + ". " + p.getNom() + " (" + p.getClass().getSimpleName() + ") - " 
                + statut);
        }
    }

    /**
     * يحسب عدد الشخصيات الميتة في القائمة.
     */
    private static int compterMorts(List<Personnage> personnages) {
        int count = 0;
        for (Personnage p : personnages) {
            if (!p.estVivant()) {
                count++;
            }
        }
        return count;
    }

    /**
     * يجلب قائمة الشخصيات الحية.
     */
    private static List<Personnage> getVivants(List<Personnage> personnages) {
        List<Personnage> vivants = new ArrayList<>();
        for (Personnage p : personnages) {
            if (p.estVivant()) {
                vivants.add(p);
            }
        }
        return vivants;
    }

    /**
     * يعلن الفائز أو التعادل بعد انتهاء القتال.
     */
    private static void declarerVainqueur(List<Personnage> personnages) {
        List<Personnage> vivants = getVivants(personnages);

        if (vivants.size() == 1) {
            Personnage vainqueur = vivants.get(0);
            System.out.println("\n" + vainqueur.getNom() + " (" 
                + vainqueur.getClass().getSimpleName() + ") est le vainqueur de la guerre !");
        } else if (vivants.isEmpty()) {
            System.out.println("\nToutes les personnages sont mortes. La guerre se termine par un désastre !");
        } else {
            System.out.println("\nIl y a plusieurs survivants. La guerre se termine sans vainqueur clair.");
            System.out.println("Les survivants sont :");
            for (Personnage p : vivants) {
                System.out.println("- " + p.getNom() + " (" + p.getClass().getSimpleName() + ")");
            }
        }
    }
}
