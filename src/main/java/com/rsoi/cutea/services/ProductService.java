package com.rsoi.cutea.services;

import com.rsoi.cutea.models.Image;
import com.rsoi.cutea.models.Product;
import com.rsoi.cutea.models.User;
import com.rsoi.cutea.repositories.ProductRepository;
import com.rsoi.cutea.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Product> listProducts(String category, String title) {
        var products = productRepository.findAll().stream();
        if (category != null && !category.isBlank()) products = products.filter(p -> p.getCategory().equalsIgnoreCase(category));
        if (title != null && !title.isBlank()) products = products.filter(p -> p.getTitle().toLowerCase().contains(title.toLowerCase()));
        return products.toList();
    }

    public Product saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        if (file1.getSize() != 0) {
            Image image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) product.addImageToProduct(toImageEntity(file2));
        if (file3.getSize() != 0) product.addImageToProduct(toImageEntity(file3));
        log.info("Saving new Product. Category {}; Title: {}", product.getCategory(), product.getTitle());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);

        return productFromDb;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) productRepository.delete(product);
        else log.error("Product with id = {} is not found", id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
