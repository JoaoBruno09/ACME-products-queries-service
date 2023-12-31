package com.isep.acme.services.impl;

import com.isep.acme.model.Product;
import com.isep.acme.model.dtos.ProductDTO;
import com.isep.acme.model.dtos.ProductDetailDTO;
import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Optional<Product> getProductBySku( final String sku ) {
        return repository.findBySku(sku);
    }

    @Override
    public Optional<ProductDTO> findBySku(String sku) {
        final Optional<Product> product = repository.findBySku(sku);
        if(!product.isEmpty()){return Optional.of(product.get().toDto());}

        return Optional.empty();
    }


    @Override
    public Iterable<ProductDTO> findByDesignation(final String designation) {
        Iterable<Product> p = repository.findByDesignation(designation);
        List<ProductDTO> pDto = new ArrayList();
        for (Product pd:p) {
            pDto.add(pd.toDto());
        }

        return pDto;
    }

    @Override
    public Iterable<ProductDTO> getCatalog() {
        Iterable<Product> p = repository.findAll();
        List<ProductDTO> pDto = new ArrayList();
        for (Product pd:p) {
            pDto.add(pd.toDto());
        }

        return pDto;
    }

    public ProductDetailDTO getDetails(String sku) {
        Optional<Product> p = repository.findBySku(sku);
        if (!p.isEmpty()){return new ProductDetailDTO(p.get().getSku(), p.get().getDesignation(), p.get().getDescription());}

        return null;
    }

}
