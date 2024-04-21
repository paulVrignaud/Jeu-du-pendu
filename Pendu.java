import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Classe Pendu qui sert à jouer au jeu du pendu seul ou à deux
 * @atuhor Paul Vrignaud
 */
public class Pendu {

    /** Mot mystère */
    private String mot;

    /** Statut si le mot mystère a été trouvé */
    private boolean motTrouve;  

    /** Nombre d'erreurs du joueur */
    private int nbErreur;

    /** ArrayList des lettres du mot mystère */
    private ArrayList<Character> listeLettresMot;

    /** ArrayList des lettres trouvés */
    private ArrayList<Character> listeLettresTrouvees;

    /** ArrayList des lettres saisies par le joueur */
    private ArrayList<Character> listeLettresSaisies;

    /** Nombre d'erreurs maximales */
    private final static int NB_ERREUR_MAX = 9;

    /**
     * Constructeur du pendu avec 1 joueur
     * @param cheminFichier chemin vers le fichier txt de mot
     */
    public Pendu(String cheminFichier) {
        this.mot = this.choixMot(cheminFichier);
        this.initialise();
    }

    /**
     * Constucteur du pendu avec 2 joueurs
     */
    public Pendu() {
        this.mot = this.choixMotJoueur();
        this.initialise();
    }

    /**
     * Initialise les paramètres généraux du pendui
     */
    private void initialise() {
        this.motTrouve = false;
        this.nbErreur = 0;
        this.listeLettresTrouvees = new ArrayList<Character>();
        this.listeLettresSaisies = new ArrayList<Character>();
        this.listeLettresMot = new ArrayList<Character>();

        this.initialiseListeLettres();
    }

    /**
     * Initialise la liste des lettres contenus dans le mot
     */
    private void initialiseListeLettres() {
        for (int i = 0; i < this.mot.length(); i++) {
            //Si la lettre n'est pas dans la liste et que ce n'est pas un espace
            if (!this.listeLettresMot.contains(this.mot.charAt(i)) && this.mot.charAt(i) != ' ') {
                this.listeLettresMot.add(this.mot.charAt(i));
            }
        }
    }

    /**
     * Permet de lancer une partie du pendu
     * @return retourne 0 si le joueur a perdu, 1 sinon
     */
    public int jouer() {
        int res = 0;
        Scanner scan = new Scanner(System.in);

        System.out.println("Commencez à deviner le mot : \n");

        // Tant que le mot n'a pas été trouvé et que le joueur n'a pas perdu
        while (!this.motTrouve && this.nbErreur < NB_ERREUR_MAX) {
            this.printMot();
            this.afficheLettresSaisies();
            
            System.out.print("tapez une lettre > ");
            char lettre = scan.nextLine().charAt(0);
            
            System.out.println();

            

            //Si la lettre à déjà été saisie auparavant
            while (this.listeLettresSaisies.contains(lettre)) {
                System.out.print("La lettre <" + lettre + "> a déjà été saisie, choisissez en une autre > ");
                lettre = scan.nextLine().charAt(0);

                System.out.println();
            }

            //rajout de la nouvelle lettre saisie
            this.listeLettresSaisies.add(lettre);

            //Affichage de la nouvelle fenêtre
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

            //Vérifie si le mot contient la lettre 
            if (this.contient(lettre)) {
                System.out.println("La lettre <" + lettre + "> est bien dans le mot mystère.");
                this.listeLettresTrouvees.add(lettre);
            } else {
                System.out.println("La lettre <" + lettre + "> n'est pas dans le mot mystère.");
                this.nbErreur += 1;
            }

            this.aTrouve();

            //Dessine l'avancement du pendu
            this.dessine();
        }

        if (this.nbErreur >= NB_ERREUR_MAX) {
            System.out.println("Vous avez perdu, le mot était " + this.mot);
        } else {
            System.out.println("Vous avez gagné !");
            System.out.println("Le mot mystère était bien " + this.mot);
            res = 1;
        }

        return res;
    }

    /**
     * Affiche les différentes lettres déjà saisies
     */
    private void afficheLettresSaisies() {
        System.out.println("Lettres saisies : " + this.listeLettresSaisies.toString());
        System.out.println();

        
    }

    /**
     * Dessine le dessin du pendu en fonction du nombre d'essais perdu
     */
    private void dessine() {
        String dessin = "";

        if (this.nbErreur == 1) {
            dessin = "\t+---------\n";
        }
        
        if (this.nbErreur == 2) {
            dessin = "\t+---------\n";
            dessin += "\t|\n";
            dessin += "\t|\n";
            dessin += "\t|\n";
            dessin += "\t|\n";
            dessin += "\t|\n";
        } 
        
        if (this.nbErreur == 3) {
            dessin = "\t+---------\n";
            dessin += "\t|        |\n";
            dessin += "\t|\n";
            dessin += "\t|\n";
            dessin += "\t|\n";
            dessin += "\t|\n";
        } 
        
        if (this.nbErreur == 4) {
            dessin = "\t+---------\n";
            dessin += "\t|        |\n";
            dessin += "\t|        o\n";
            dessin += "\t|\n";
            dessin += "\t|\n";
        }
        
        if (this.nbErreur == 5) {
            dessin = "\t+---------\n";
            dessin += "\t|        |\n";
            dessin += "\t|        o\n";
            dessin += "\t|        |\n";
            dessin += "\t|\n";
            dessin += "\t|\n";
        }
        
        if (this.nbErreur == 6) {
            dessin = "\t+---------\n";
            dessin += "\t|        |\n";
            dessin += "\t|        o\n";
            dessin += "\t|       /|\n";
            dessin += "\t|\n";
            dessin += "\t|\n";
        }
        
        if (this.nbErreur == 7) {
            dessin = "\t+---------\n";
            dessin += "\t|        |\n";
            dessin += "\t|        o\n";
            dessin += "\t|       /|\\ \n";
            dessin += "\t|\n";
            dessin += "\t|\n";
        }   
        
        if (this.nbErreur == 8) {
            dessin = "\t+---------\n";
            dessin += "\t|        |\n";
            dessin += "\t|        o\n";
            dessin += "\t|       /|\\ \n";
            dessin += "\t|       /\n";
            dessin += "\t|\n";
        }

        if (this.nbErreur == 9) {
            dessin = "\t+---------\n";
            dessin += "\t|        |\n";
            dessin += "\t|        o\n";
            dessin += "\t|       /|\\ \n";
            dessin += "\t|       / \\ \n";
            dessin += "\t|\n";
        }

        System.out.println(dessin);
        System.out.println();
    }

    /**
     * Vérifie si une lettre est présente dans le mot
     * @param lettre la lettre à vérifier
     * @return true si la lettre est dans le mot, false sinon
     */
    private boolean contient(char lettre) {
        boolean ret = false;

        if (this.listeLettresMot.contains(lettre)) {
            ret= true;
        }
        return ret;
    }

    /**
     * Méthode qui définir si le joueur a gagné
     */
    private void aTrouve() {
        if (this.listeLettresMot.size() == this.listeLettresTrouvees.size()) {
            this.motTrouve = true;
        }
    }

    /**
     * Affiche chaques lettres du mot si la lettre à déjà été trouvé auparavant, sinon -
     */
    private void printMot() {
        String ret = "\t";

        for (int i = 0; i < this.mot.length(); i++) {
            if (this.mot.charAt(i) == ' '){
                ret += " ";
            } else if (this.listeLettresTrouvees.contains(this.mot.charAt(i))) {
                ret += this.mot.charAt(i);
            } else {
                ret += "-";
            }
        }
        System.out.println(ret);
        System.out.println();
    }

    /**
     * Choisi un mot aléatoirement dans un fichier txt
     * @param cheminFichier chemin vers le fichier txt de mot
     * @return le mot choisi
     */
    private String choixMot(String cheminFichier) {
        String ret = null;

        try {
            Scanner scan = new Scanner(new File(cheminFichier));

            ArrayList<String> listeMots = new ArrayList<String>();
            String mot = null;

            while(scan.hasNextLine()) {
                mot = scan.nextLine();
                listeMots.add(mot);
            }
            int choix = (int) (Math.random() * listeMots.size());
            ret = listeMots.get(choix);

        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable");
        }
        return ret;
    }

    /**
     * Permet au joueur 1 de choisir un mot
     * @return le mot choisi par le joueur 1
     */
    private String choixMotJoueur() {
        String ret = null;

        System.out.println("Joueur 1 saisissez un mot secret :");
        Scanner scan = new Scanner(System.in);

        ret = scan.nextLine();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        return ret;
    } 
}