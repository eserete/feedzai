import java.util.LinkedList;
import java.util.Queue;

public class PaymentService {

    public static final SumAggregator DEFAULT_AGGREGATOR = new SumAggregator();
    private static final long DEFAULT_WINDOW = 10000L;

    private final PaymentAggregator aggregator;
    private final Queue<Payment> payments = new LinkedList<>();
    private long window = DEFAULT_WINDOW;

    public PaymentService() {
        this(DEFAULT_AGGREGATOR);
    }

    public PaymentService(PaymentAggregator aggregator) {
        super();
        this.aggregator = aggregator;
    }

    public PaymentService(long window) {
        this();
        this.window = window;
    }

    public long post(long timestamp, long amount) {
        payments.add(new Payment(timestamp, amount));
        var paymentIterator = payments.iterator();
        aggregator.reset();
        while (paymentIterator.hasNext()) {
            var payment = paymentIterator.next();
            if (isInRange(payment, timestamp)) {
                aggregator.add(payment);
            } else {
                paymentIterator.remove();
            }
        }
        return aggregator.result();
    }

    private boolean isInRange(Payment payment, long timestamp) {
        return payment.timestamp() > (timestamp - this.window);
    }
}
