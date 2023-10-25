package service;

public class PaypalService implements OnlinePaymentService {
  private double fee;
  private double monthInterest;

  public PaypalService(double fee, double monthInterest) {
    this.fee = fee;
    this.monthInterest = monthInterest;
  }

  @Override
  public double paymentFee(double amount) {
    return this.fee * amount;
  }

  @Override
  public double interest(double amount, Integer months) {
    return amount * this.monthInterest * months;
  }
}
