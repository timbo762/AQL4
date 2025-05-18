package partie3;

import org.example.partie3.Product;
import org.example.partie3.ProductApiClient;
import org.example.partie3.ProductApiException;
import org.example.partie3.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductApiClient apiClient;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(apiClient);
    }

    @Test
    void getProduct_Success() throws ProductApiException {
        // Arrange
        String productId = "1234";
        Product expectedProduct = new Product(productId, "RIFKUS", 100.00, "Magnifique ");
        when(apiClient.getProduct(productId)).thenReturn(expectedProduct);

        // Act
        Product result = productService.getProduct(productId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedProduct.getId(), result.getId());
        assertEquals(expectedProduct.getName(), result.getName());
        assertEquals(expectedProduct.getPrice(), result.getPrice());
        assertEquals(expectedProduct.getDescription(), result.getDescription());
        verify(apiClient, times(1)).getProduct(productId);
    }

    @Test
    void getProduct_ApiFailure() {
        // Arrange
        String productId = "1234";
        when(apiClient.getProduct(productId)).thenThrow(new ProductApiException("API Error"));

        // Act & Assert
        assertThrows(ProductApiException.class, () -> productService.getProduct(productId));
        verify(apiClient, times(1)).getProduct(productId);
    }

    @Test
    void getProduct_NullProductId() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> productService.getProduct(null));
        verify(apiClient, never()).getProduct(any());
    }

    @Test
    void getProduct_EmptyProductId() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> productService.getProduct(""));
        verify(apiClient, never()).getProduct(any());
    }

    @Test
    void getProduct_BlankProductId() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> productService.getProduct("   "));
        verify(apiClient, never()).getProduct(any());
    }
}