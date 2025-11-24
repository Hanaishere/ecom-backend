package com.telusko.ecom_proj1.service;

import com.telusko.ecom_proj1.model.Product;
import com.telusko.ecom_proj1.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;
    public List<Product> getAllProducts() {
       return repo.findAll();
    }

    public Product getProductById(int id) {
        return  repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        // Convert fields to correct types
        product.setPrice(new BigDecimal(product.getPrice().toString()));
        product.setStockQuantity(Integer.parseInt(String.valueOf(product.getStockQuantity())));

        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return repo.save(product);
    }


    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setImageData(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}
