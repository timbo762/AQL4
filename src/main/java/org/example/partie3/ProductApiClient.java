package org.example.partie3;



public interface ProductApiClient {
    Product getProduct(String productId) throws ProductApiException;
}