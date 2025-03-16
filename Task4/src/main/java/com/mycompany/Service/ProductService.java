package com.mycompany.Service;

import com.mycompany.Repository.CategoryRepository;
import com.mycompany.Repository.ProductRepository;
import com.mycompany.model.Category;
import com.mycompany.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    public Product updateProduct(Long id,Product product) {
        Product oldProduct = productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        return productRepository.save(oldProduct);

    }
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
    private List<Category> getCategoriesFromIds(List<Category> categoryList) {
        return categoryList.stream()
                .map(cat -> categoryRepository.findById(cat.getId())
                        .orElseThrow(() -> new RuntimeException("Category not found")))
                .collect(Collectors.toList());
    }
}
