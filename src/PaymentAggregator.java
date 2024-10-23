public interface PaymentAggregator {

    void add(Payment payment);

    long result();

    void reset();
}
