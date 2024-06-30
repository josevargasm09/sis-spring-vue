package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Brand;
import com.bezkoder.spring.security.jwt.models.Category;
import com.bezkoder.spring.security.jwt.models.Product;
import com.bezkoder.spring.security.jwt.payload.request.ProductDTO;
import com.bezkoder.spring.security.jwt.repository.BrandRepository;
import com.bezkoder.spring.security.jwt.repository.CategoryRepository;
import com.bezkoder.spring.security.jwt.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getCategory() != null ? product.getCategory().getId() : null,
            product.getBrand() != null ? product.getBrand().getId() : null
        );
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        Category category = categoryRepository.findById(productDTO.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        product.setCategory(category);

        Brand brand = brandRepository.findById(productDTO.getBrandId())
            .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        product.setBrand(brand);

        return product;
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return convertToDTO(product);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());

        Category category = categoryRepository.findById(productDTO.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        existingProduct.setCategory(category);

        Brand brand = brandRepository.findById(productDTO.getBrandId())
            .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        existingProduct.setBrand(brand);

        Product updatedProduct = productRepository.save(existingProduct);
        return convertToDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productRepository.deleteById(id);
    }
}
