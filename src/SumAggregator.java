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
        return payments.stream().map(Payment::amount).reduce(0L, Long::sum);
    }

    @Override
    public void reset() {
        payments = new LinkedList<>();
    }
}
