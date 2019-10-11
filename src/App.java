import bo.*;
import dal.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.*;
import java.io.*;
import java.util.List;

public class App {

    private static Scanner sc = new Scanner(System.in);
    private static IDAO<Integer, Compte> compteDAO = new CompteDAO();
    private static IDAO<Integer, CompteSimple> simpleDAO = new CompteSimpleDAO();
    private static IDAO<Integer, CompteEpargne> epargneDAO = new CompteEpargneDAO();
    private static IDAO<Integer, ComptePayant> payantDAO = new ComptePayantDAO();
    private static IDAO<Integer, Agence> agenceDAO = new AgenceDAO();
    private static IDAO<Integer, Operation> operationDAO = new OperationDAO();
    private static boolean demarrage = true;
    private static boolean arret = false;

    public static void affichageMenu() throws SQLException, IOException, ClassNotFoundException {

        int input;

        do {
            if(demarrage){
                //ASCII Art
                int width = 100;
                int height = 35;

                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics g = image.getGraphics();
                g.setFont(new Font("SansSerif", Font.BOLD, 10));

                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                graphics.drawString("S E G A B A N K", 10, 20);

                System.out.println();
                for (int y = 0; y < height; y++) {
                    StringBuilder sb = new StringBuilder();
                    for (int x = 0; x < width; x++) {
                        sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");
                    }
                    if (sb.toString().trim().isEmpty()) {
                        continue;
                    }
                    System.out.println(sb);
                }
                demarrage = false;
            }

            System.out.println();
            System.out.println("1 - Comptes");
            System.out.println("2 - Agences");
            System.out.println("3 - Créer compte simple");
            System.out.println("4 - Créer compte épargne");
            System.out.println("5 - Créer compte payant");
            System.out.println("6 - Modifier compte");
            System.out.println("7 - Supprimer compte");
            System.out.println("8 - Versement");
            System.out.println("9 - Retrait");
            System.out.println("10 - Exporter");
            System.out.println("\nEntrez une valeur :");
            try {
                input = sc.nextInt();
            }catch (InputMismatchException e) {
                input = -1;
            }finally {
                sc.nextLine();
            }
        } while (input < 1 || input > 10);

        switch (input){
            case 1:
                listCompte();
                break;
            case 2:
                listAgence();
                break;
            case 3:
                createCompteSimple();
                break;
            case 4:
                createCompteEpargne();
                break;
            case 5:
                createComptePayant();
                break;
            case 6:
                updateCompte();
                break;
            case 7:
                deleteCompte();
                break;
            case 8:
                versement();
                break;
            case 9:
                retrait();
                break;
            case 10:
                exporter();
                break;
        }
            affichageMenu();
    }

    private static void listCompte() throws SQLException, IOException, ClassNotFoundException {

        //Boucle pour récupérer tous les comptes
        for (Compte compte : compteDAO.findAll()){
            System.out.println("Compte " + compte.getId() + " | " + compte.getSolde() + "€ | Agence " + compte.getAgence());
        }
    }

    private static void listAgence() throws SQLException, IOException, ClassNotFoundException {

        //Boucle pour récupérer toutes les agences
        for(Agence agence : agenceDAO.findAll()){
            System.out.println("Agence " + agence.getId() + " | " + agence.getAdresse() + " | Code " + agence.getCode());
        }
    }

    private static void createCompteSimple() {

        try {
            System.out.print("Entrez un solde : ");
            double solde = sc.nextDouble();
            System.out.println("Choisissez une agence : ");
            listAgence();
            int agenceId = sc.nextInt();
            System.out.println("Entrez un découvert : ");
            double decouvert = sc.nextDouble();
            Compte compte = new Compte(solde);
            compte.setAgence(agenceId);
            compteDAO.create(compte);
            CompteSimple simpleCompte = new CompteSimple(compte.getSolde(), decouvert, compte.getId());
            simpleDAO.create(simpleCompte);
            System.out.println("Compte simple créé !");
        }
        catch (InputMismatchException e){
            System.out.println("Erreur");
            System.out.println(e.getMessage());
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            sc.nextLine();
        }
    }

    private static void createCompteEpargne() {

        try {
            System.out.print("Entrez un solde : ");
            double solde = sc.nextDouble();
            System.out.println("Choisissez une agence : ");
            listAgence();
            int agenceId = sc.nextInt();
            System.out.println("Entrez un taux d'intéret : ");
            float tauxInteret = sc.nextFloat();
            Compte compte = new Compte(solde);
            compte.setAgence(agenceId);
            compteDAO.create(compte);
            CompteEpargne epargne = new CompteEpargne(compte.getSolde(), tauxInteret, compte.getId());
            epargneDAO.create(epargne);
            System.out.println("Compte épargne créé !");
        }
        catch (InputMismatchException e){
            System.out.println("Erreur");
            System.out.println(e.getMessage());
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            sc.nextLine();
        }
    }

    private static void createComptePayant() {

        try {
            System.out.print("Entrez un solde : ");
            double solde = sc.nextDouble();
            System.out.println("Choisissez une agence : ");
            listAgence();
            int agenceId = sc.nextInt();
            Compte compte = new Compte(solde);
            compte.setAgence(agenceId);
            compteDAO.create(compte);
            ComptePayant payantCompte = new ComptePayant(compte.getSolde(), compte.getId());
            payantDAO.create(payantCompte);
            System.out.println("Compte payant créé !");
        }
        catch (InputMismatchException e){
            System.out.println("Erreur");
            System.out.println(e.getMessage());
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            sc.nextLine();
        }
    }

    private static void updateCompte() throws SQLException, IOException, ClassNotFoundException {

        listCompte();
        int input;
        List<Integer> id = new ArrayList<>();

        for(Compte item : compteDAO.findAll()){
            id.add(item.getId());
        }

        boolean first = true;
        do {
            if (!first){
                System.out.println("Erreur");
            }
            System.out.println("Quel compte souhaitez-vous modifier ?");
            try {
                input = sc.nextInt();
            }catch (InputMismatchException e) {
                input = -1;
            }finally {
                sc.nextLine();
            }
            first = false;
        }while (input < 0 || input > Collections.max(id));
        try {
            Compte compte = compteDAO.findById(input);
            System.out.println("Nouveau solde :");
            compte.setSolde(sc.nextInt());
            System.out.println("Nouvelle agence : ");
            listAgence();
            compte.setAgence(sc.nextInt());
            System.out.println("Opération effectuée");
            compteDAO.update(compte);
        } catch(IOException e){
            System.out.println("Erreur");
        }
    }

    private static void deleteCompte() throws SQLException, IOException, ClassNotFoundException {

        listCompte();
        int input;
        List<Integer> ids = new ArrayList<>();
        for(Compte item : compteDAO.findAll()){
            ids.add(item.getId());
        }
        boolean first = true;
        do {
            if (!first){
                System.out.println("Erreur");
            }
            try {
                input = sc.nextInt();
            }catch (InputMismatchException e) {
                input = -1;
            }finally {
                sc.nextLine();
            }
            first = false;
        }while (input < 0 || input > Collections.max(ids));
        compteDAO.delete(compteDAO.findById(input));
    }

    private static void retrait() {

        System.out.println("Choisissez un compte  :");
        int user;

        try {
            listCompte();
            user = sc.nextInt();
            Compte compte = compteDAO.findById(user);
            System.out.println("Montant : ");
            double montant = sc.nextDouble();
            compte.retrait(montant);
            compteDAO.update(compte);
            Operation ope = new Operation(montant, "Retrait", compte.getId(), compte.getAgence());
            operationDAO.create(ope);
        } catch (InputMismatchException ex){
            System.out.println("Valeur invalide");
        } catch (NullPointerException ex){
            System.out.println("Erreur");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void versement() {

        System.out.println("Choisissez un compte  :");
        int user;

        try {
            listCompte();
            user = sc.nextInt();
            Compte compte = compteDAO.findById(user);
            System.out.println("Montant : ");
            double montant = sc.nextDouble();
            compte.versement(montant);
            compteDAO.update(compte);
            Operation ope = new Operation(montant, "Virement", compte.getId(), compte.getAgence());
            operationDAO.create(ope);
        } catch (InputMismatchException ex){
            System.out.println("Valeur invalide");
        } catch (NullPointerException ex){
            System.out.println("Erreur");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void exporter() throws UnsupportedEncodingException {

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./resources/export.csv")))){

            StringBuffer header = new StringBuffer();
            header.append("Montant");
            header.append(";");
            header.append("Type");
            header.append(";");
            header.append("Compte");
            header.append(";");
            header.append("Agence");
            header.append(";");

            bw.write(header.toString());
            bw.append("\n");

            for (Operation item : operationDAO.findAll()){

                StringBuffer line = new StringBuffer();
                line.append(item.getMontant());
                line.append(";");
                line.append(item.getTransaction());
                line.append(";");
                line.append(item.getCompteId());
                line.append(";");
                line.append(item.getAgenceId());

                bw.write(line.toString());
                bw.append("\n");
            }
            bw.flush();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("Erreur");
        }
    }

    public static void main(String... args) throws SQLException, IOException, ClassNotFoundException {

        affichageMenu();
    }
}