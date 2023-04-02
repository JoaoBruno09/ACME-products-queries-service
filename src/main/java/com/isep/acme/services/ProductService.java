package com.isep.acme.services;

import java.util.Optional;

import com.isep.acme.model.Product;
import com.isep.acme.model.dtos.ProductDTO;
import com.isep.acme.model.dtos.ProductDetailDTO;

public interface ProductService {

    Optional<ProductDTO> findBySku(final String sku);

    Optional<Product> getProductBySku( final String sku );

    Iterable<ProductDTO> findByDesignation(final String designation);

    Iterable<ProductDTO> getCatalog();

    ProductDetailDTO getDetails(final String sku);

}
