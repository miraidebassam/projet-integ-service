package com.esmt.product_service.services;

import com.esmt.product_service.models.Produit;
import com.esmt.product_service.repository.ProduitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository productRepository;

    public List<Produit> getAllProducts() {
        return productRepository.findAll();
    }

    public Produit getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    public Produit createProduct(Produit product) {
        return productRepository.save(product);
    }

    public Produit updateProduct(Long id, Produit updatedProduct) {
        Produit existingProduct = getProductById(id);
        existingProduct.setNom(updatedProduct.getNom());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrix(updatedProduct.getPrix());
        existingProduct.setQteStock(updatedProduct.getQteStock());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        Produit existingProduct = getProductById(id);
        productRepository.delete(existingProduct);
    }

}
