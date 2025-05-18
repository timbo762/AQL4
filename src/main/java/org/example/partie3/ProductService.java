package org.example.partie3;

public class ProductService {
    private final ProductApiClient apiClient;

    public ProductService(ProductApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Product getProduct(String productId) throws ProductApiException {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException(" ID cannot be null or empty");
        }
        return apiClient.getProduct(productId);
    }
}