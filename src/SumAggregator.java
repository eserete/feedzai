import java.util.LinkedList;
import java.util.Queue;

public class SumAggregator implements PaymentAggregator {

    private Queue<Payment> payments = new LinkedList<>();

    @Override
    public void add(Payment payment) {
        payments.add(payment);
    }

    @Override
    public long result() {
        var result = 0L;
        for (Payment payment : payments) {
            result += payment.amount();
        }
        return result;
    }

    @Override
    public void reset() {
        payments = new LinkedList<>();
    }
}
