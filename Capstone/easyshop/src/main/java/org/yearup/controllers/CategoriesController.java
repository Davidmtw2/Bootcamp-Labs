package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

// add the annotations to make this a REST controller
@RestController
// add the annotation to make this controller the endpoint for the following url
    // http://localhost:8080/categories
@RequestMapping("categories")
// add annotation to allow cross site origin requests
@CrossOrigin
public class CategoriesController
{
    // create an Autowired controller to inject the categoryDao and ProductDao
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ProductDao productDao;



    // add the appropriate annotation for a get action
    @GetMapping
    @PreAuthorize(("permitAll()"))
    public ResponseEntity<List<Category>> getAll() {
        // find and return all categories
        var categories = categoryDao.getAllCategories();

        if (categories.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "It is empty");
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // add the appropriate annotation for a get action
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Category> getById(@PathVariable int id) throws ResponseStatusException {
        // get the category by id
        var categories = categoryDao.getById(id);

        if (categories == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping("{categoryId}/products")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Product>> getProductsById(@PathVariable int categoryId) {
        // get a list of product by categoryId
        var products = productDao.listByCategoryId(categoryId);

        if (products.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // add annotation to call this method for a POST action
    @PostMapping
    // add annotation to ensure that only an ADMIN can call this function
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        // insert the category
        try{
            var categoryCreated = categoryDao.create(category);
            return new ResponseEntity<>(categoryCreated, HttpStatus.CREATED);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
        }
    }

    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    @PutMapping("{id}")
    // add annotation to ensure that only an ADMIN can call this function
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        var getCategory = categoryDao.getById(id);

        if (getCategory == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        // update the category by id

        var categoryUpdated = categoryDao.update(id, category);
        return new ResponseEntity<>(categoryUpdated, HttpStatus.OK);
    }


    // add annotation to call this method for a DELETE action - the url path must include the categoryId
    @DeleteMapping("/{id}")
    // add annotation to ensure that only an ADMIN can call this function
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id)
    {
        // delete the category by id
        var getCategory = categoryDao.getById(id);

        if (getCategory == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, that category is not found, please try again!");

        categoryDao.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
