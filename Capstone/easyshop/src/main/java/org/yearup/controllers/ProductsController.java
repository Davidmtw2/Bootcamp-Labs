package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.models.Product;
import org.yearup.data.ProductDao;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("products")
@CrossOrigin
public class ProductsController
{
    @Autowired
    private ProductDao productDao;

    @Autowired
    public ProductsController(ProductDao productDao)
    {
        this.productDao = productDao;
    }

    @GetMapping("")
    @PreAuthorize("permitAll()")
    public List<Product> search(@RequestParam(name="cat", required = false) Integer categoryId,
                                @RequestParam(name="minPrice", required = false) BigDecimal minPrice,
                                @RequestParam(name="maxPrice", required = false) BigDecimal maxPrice,
                                @RequestParam(name="color", required = false) String color
                                )
    {
        try
        {
            return productDao.search(categoryId, minPrice, maxPrice, color);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public Product getById(@PathVariable int id )
    {
            var product = productDao.getById(id);

            if(product == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, " product not found");

            return product;

    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Product> addProduct(@RequestBody Product product)
    {
        try
        {
            var createProduct = productDao.create(product);

            return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product)
    {
        var findProduct = productDao.getById(id);

        if (findProduct == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " product not found");
        var updatedProduct = productDao.update(id, product);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id)
    {
        var product = productDao.getById(id);

        if(product == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        productDao.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
