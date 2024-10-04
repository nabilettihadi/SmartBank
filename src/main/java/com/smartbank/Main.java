package com.smartbank;

import com.smartbank.entities.CreditRequest;
import com.smartbank.repositories.CreditRequestRepository;
import com.smartbank.repositories.impl.CreditRequestRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static CreditRequestRepository creditRequestRepository = new CreditRequestRepositoryImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Créer une nouvelle demande de crédit");
            System.out.println("2. Mettre à jour une demande de crédit");
            System.out.println("3. Supprimer une demande de crédit");
            System.out.println("4. Trouver une demande par ID");
            System.out.println("5. Afficher toutes les demandes");
            System.out.println("6. Quitter");
            System.out.print("Choisissez une option : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createCreditRequest();
                    break;
                case 2:
                    updateCreditRequest();
                    break;
                case 3:
                    deleteCreditRequest();
                    break;
                case 4:
                    findCreditRequestById();
                    break;
                case 5:
                    findAllCreditRequests();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private static void createCreditRequest() {
        CreditRequest newRequest = new CreditRequest();
        System.out.println("Création d'une nouvelle demande de crédit...");

        System.out.print("Montant : ");
        newRequest.setAmount(scanner.nextBigDecimal());

        System.out.print("Durée (en mois) : ");
        newRequest.setDuration(scanner.nextInt());

        System.out.print("Paiements mensuels : ");
        newRequest.setMonthlyPayments(scanner.nextBigDecimal());

        scanner.nextLine();
        System.out.print("Email : ");
        newRequest.setEmail(scanner.nextLine());

        System.out.print("Téléphone mobile : ");
        newRequest.setMobilePhone(scanner.nextLine());

        System.out.print("Prénom : ");
        newRequest.setFirstName(scanner.nextLine());

        System.out.print("Nom : ");
        newRequest.setLastName(scanner.nextLine());

        System.out.print("Numéro CIN : ");
        newRequest.setCinNumber(scanner.nextLine());

        System.out.print("Date de naissance (AAAA-MM-JJ) : ");
        newRequest.setBirthDate(LocalDate.parse(scanner.nextLine()));

        System.out.print("Date d'embauche (AAAA-MM-JJ) : ");
        newRequest.setHiringDate(LocalDate.parse(scanner.nextLine()));

        System.out.print("Revenu total : ");
        newRequest.setTotalRevenue(scanner.nextBigDecimal());

        System.out.print("Avez-vous des crédits en cours (true/false) ? : ");
        newRequest.setHasOngoingCredits(scanner.nextBoolean());

        CreditRequest savedRequest = creditRequestRepository.save(newRequest);
        System.out.println("Demande de crédit sauvegardée avec ID : " + savedRequest.getId());
    }

    private static void updateCreditRequest() {
        System.out.print("Entrez l'ID de la demande à mettre à jour : ");
        Long id = scanner.nextLong();
        CreditRequest request = creditRequestRepository.findById(id);

        if (request != null) {
            scanner.nextLine();
            System.out.print("Nouveau prénom (actuel : " + request.getFirstName() + ") : ");
            String newFirstName = scanner.nextLine();
            if (!newFirstName.isEmpty()) {
                request.setFirstName(newFirstName);
            }

            System.out.print("Nouveau montant (actuel : " + request.getAmount() + ") : ");
            BigDecimal newAmount = scanner.nextBigDecimal();
            request.setAmount(newAmount);

            CreditRequest updatedRequest = creditRequestRepository.update(request);
            System.out.println("Demande mise à jour : " + updatedRequest.getFirstName() + ", Montant : " + updatedRequest.getAmount());
        } else {
            System.out.println("Demande de crédit non trouvée pour l'ID : " + id);
        }
    }

    private static void deleteCreditRequest() {
        System.out.print("Entrez l'ID de la demande à supprimer : ");
        Long id = scanner.nextLong();

        boolean deleted = creditRequestRepository.deleteById(id);
        if (deleted) {
            System.out.println("Demande de crédit supprimée.");
        } else {
            System.out.println("Demande de crédit non trouvée pour l'ID : " + id);
        }
    }

    private static void findCreditRequestById() {
        System.out.print("Entrez l'ID de la demande à récupérer : ");
        Long id = scanner.nextLong();

        CreditRequest request = creditRequestRepository.findById(id);
        if (request != null) {
            System.out.println("Demande trouvée : " + request.getFirstName() + " " + request.getLastName() + ", Montant : " + request.getAmount());
        } else {
            System.out.println("Aucune demande trouvée pour l'ID : " + id);
        }
    }

    private static void findAllCreditRequests() {
        List<CreditRequest> requests = creditRequestRepository.findAll();
        System.out.println("Liste des demandes de crédit : ");
        for (CreditRequest request : requests) {
            System.out.println("ID : " + request.getId() + ", Nom : " + request.getFirstName() + " " + request.getLastName());
        }
    }
}
