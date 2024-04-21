import java.util.Scanner;

public class Jeu {
    public static void main(String[] args) {
        jouer();
    }

    public static void jouer() {
        Pendu pendu;

        System.out.println("\t\t=== Jeu du Pendu ===\n");
        Scanner scan = new Scanner(System.in);

        int point = 0;
        boolean arretJeu = false;

        while(!arretJeu) {

            System.out.print("Combien de joueur joue ? (1 - 2) > ");

            int nbJoueur = scan.nextInt();

            while (nbJoueur != 1 && nbJoueur != 2) {
                nbJoueur = scan.nextInt();
            }

            System.out.println();

            if (nbJoueur == 1) {
                pendu = new Pendu("../ws/mots.txt");
            } else {
                pendu = new Pendu();
            }

            point += pendu.jouer();
            System.out.println("Vous avez actuellement " + point + " point(s).");
            System.out.print("Fin de partie, voulez-vous rejouer ? (O/N) >");

            String reponse = scan.nextLine();

            while (!reponse.equalsIgnoreCase("O") && !reponse.equalsIgnoreCase("N")) {
                reponse = scan.nextLine();
            }

            if (reponse.equalsIgnoreCase("N")) {
                arretJeu = true;
            }
        }
    }
}
