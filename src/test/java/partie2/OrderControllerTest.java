package partie2;

import org.example.partie2.Order;
import org.example.partie2.OrderController;
import org.example.partie2.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class OrderControllerTest {
    @Mock
    private OrderService orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {

        Order testOrder = new Order(1234, "RIFKUS", 10, 60.50);

        OrderController orderController = new OrderController(orderService);

        orderController.createOrder(testOrder);

        verify(orderService).createOrder(testOrder);
    }
} 