package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import entities.Contract;
import entities.Installment;
import service.ContractService;
import service.OnlinePaymentService;
import service.PaypalService;

public class Project {

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Scanner sc = new Scanner(System.in);
    System.out.println("Input the contract data:");
    System.out.print("Contract number :");
    int contractNumber = sc.nextInt();
    System.out.print("Contract date (DD/MM/YYYY) : ");
    sc.nextLine();
    LocalDate contractDate = dateFormater(sc.nextLine());
    System.out.print("Contract value: ");
    double contractValue = sc.nextDouble();
    System.out.print("Input the number of installments: ");
    int installments = sc.nextInt();
    Contract contract = new Contract(contractNumber, contractDate, contractValue);
    OnlinePaymentService paymentService = new PaypalService(0.02, 0.01);
    ContractService contractService = new ContractService(paymentService);
    contractService.processContract(contract, installments);
    System.out.println("Installments:");
    for (Installment installment : contract.getInstallments()) {
      System.out.println(installment);
    }

    sc.close();
  }

  private static LocalDate dateFormater(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return LocalDate.parse(date, formatter);
  }
}
