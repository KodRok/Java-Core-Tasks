package school.sorokin.javacore.testing.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void processOrderShouldPrintSuccessMessageIfSaveTrue() {
        Order testOrder = new Order(1, "Айфон", 1, 1200.0);
        when(orderRepository.saveOrder(testOrder)).thenReturn(1);
        String result = orderService.processOrder(testOrder);
        assertEquals("Order processed successfully", result);
        verify(orderRepository).saveOrder(testOrder);
    }

    @Test
    void processOrderShouldPrintFailMessageIfSaveFalse() {
        Order testOrder = new Order(2, "Смартфон", 1, 800.0);
        when(orderRepository.saveOrder(testOrder)).thenReturn(0);
        String result = orderService.processOrder(testOrder);
        assertEquals("Order processing failed", result);
        verify(orderRepository).saveOrder(testOrder);
    }

    @Test
    void processOrderShouldPrintExceptionMessageIfException() {
        Order testOrder = new Order(3, "Нокиа", 1, 250.0);
        when(orderRepository.saveOrder(testOrder)).thenThrow
                (new RuntimeException("Что-то пошло не так"));
        String result = orderService.processOrder(testOrder);
        assertEquals("Order processing failed", result);
        verify(orderRepository).saveOrder(testOrder);
    }

    @Test
    void calculateTotalShouldReturnZeroIfOrderNull() {
        when(orderRepository.getOrderById(5)).thenReturn(Optional.empty());
        double total = orderService.calculateTotal(5);
        assertEquals(0.0, total);
        verify(orderRepository).getOrderById(5);
    }

    @Test
    void calculateTotalShouldReturnCorrectIfOrderExist() {
        Order testOrder = new Order(4, "Самсунг", 3, 100.0);
        when(orderRepository.getOrderById(4)).thenReturn(Optional.of(testOrder));
        double total = orderService.calculateTotal(4);
        assertEquals(300.0, total);
        verify(orderRepository).getOrderById(4);
    }

    @Test
    void calculateTotalShouldReturnZeroIfQuantityIsZero() {
        Order testOrder = new Order(6, "Хуавей", 0, 50.0);
        when(orderRepository.getOrderById(6)).thenReturn(Optional.of(testOrder));
        double total = orderService.calculateTotal(6);
        assertEquals(0.0, total);
        verify(orderRepository).getOrderById(6);
    }

    @Test
    void calculateTotalShouldReturnZeroIfPriceIsZero() {
        Order testOrder = new Order(7, "Филипс", 1, 0.0);
        when(orderRepository.getOrderById(7)).thenReturn(Optional.of(testOrder));
        double total = orderService.calculateTotal(7);
        assertEquals(0.0, total);
        verify(orderRepository).getOrderById(7);
    }
}