package com.souzatech.service;

import com.souzatech.dto.ProductDTO;
import com.souzatech.entity.ProductEntity;
import com.souzatech.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<ProductDTO> findAllProducts() {
        List<ProductDTO> products = new ArrayList<>();

        productRepository.findAll().stream().forEach(item->{
            products.add(mapProductEntityToDTO(item));
        });

        return products;
    }

    public void createNewProduct(ProductDTO productDTO) {
        productRepository.persist(mapProductDtoToEntity(productDTO));
    }

    public void changeProduct(Long id, ProductDTO productDTO) {

        ProductEntity productEntity = productRepository.findById(id);

        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setPrice(productDTO.getPrice());

        productRepository.persist(productEntity);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private static ProductEntity mapProductDtoToEntity(ProductDTO productDTO) {

        ProductEntity productEntity = new ProductEntity();

        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setPrice(productDTO.getPrice());

        return productEntity;
    }

    private ProductDTO mapProductEntityToDTO(ProductEntity product){
        ProductDTO productDTO = new ProductDTO();

        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategory(product.getCategory());
        productDTO.setPrice(product.getPrice());


        return productDTO;
    }
}
