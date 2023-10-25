package service;

import java.time.LocalDate;
import entities.Contract;
import entities.Installment;

public class ContractService {
  private OnlinePaymentService paymentService;

  public ContractService(OnlinePaymentService paymentService) {
    this.paymentService = paymentService;
  }

  public void processContract(Contract contract, Integer months) {
    double installmentBaseValue = contract.getTotalValue() / months;
    for (int month = 1; month <= months; month += 1) {
      double interest = this.paymentService.interest(installmentBaseValue, month);
      double fee = this.paymentService.paymentFee(installmentBaseValue + interest);
      double totalInstallment = installmentBaseValue + interest + fee;
      LocalDate date = contract.getDate().plusMonths(month);
      contract.getInstallments().add(new Installment(date, totalInstallment));
    }
  }
}
