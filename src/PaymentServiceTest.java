import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentServiceTest {

    @Test
    public void testDefaultWindow() {
        PaymentService paymentService = new PaymentService();
        assertEquals(50, paymentService.post(1000, 50));
        assertEquals(150, paymentService.post(2000, 100));
        assertEquals(160, paymentService.post(4000, 10));

        assertEquals(130, paymentService.post(11000, 20));
        assertEquals(100, paymentService.post(30000, 100));
    }

    @Test
    public void testCase20000() {
        PaymentService paymentService = new PaymentService(20000L);
        assertEquals(50, paymentService.post(1000, 50));
        assertEquals(150, paymentService.post(2000, 100));
        assertEquals(160, paymentService.post(4000, 10));
        assertEquals(180, paymentService.post(11000, 20));
        assertEquals(120, paymentService.post(30000, 100));
    }
}

