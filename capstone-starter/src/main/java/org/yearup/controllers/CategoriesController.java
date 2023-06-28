package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.HashMap;
import java.util.List;

// add the annotations to make this a REST controller
@RestController
// add the annotation to make this controller the endpoint for the following url
    // http://localhost:8080/categories
@RequestMapping("/categories")
// add annotation to allow cross site origin requests
@CrossOrigin
public class CategoriesController
{
    private CategoryDao categoryDao;
    private ProductDao productDao;


    // create an Autowired controller to inject the categoryDao and ProductDao
    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao ){
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    // add the appropriate annotation for a get action
    @GetMapping
    public List<Category> getAllCategories(){
        // find and return all categories
        return categoryDao.getAllCategories();
    }

    // add the appropriate annotation for a get action
    @GetMapping("/{id}")
    public Category getById(@PathVariable int id){
        // get the category by id
        return categoryDao.getById(id);
    }
    @PostMapping
    @ResponseStatus(value= HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category){
        return categoryDao.create(category);
    }

    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products

    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId){
        // get a list of product by categoryId
        return null;
    }

    // add annotation to call this method for a POST action
    // add annotation to ensure that only an ADMIN can call this function
    public Category addCategory(@RequestBody Category category){
        // insert the category
        return null;
    }

    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @PutMapping("/{id}")
    public HashMap<String, String> updateCategory(@PathVariable int id, @RequestBody Category category){
        categoryDao.update(id, category);

        HashMap<String, String> response = new HashMap<>();
        response.put("status", "Successful");
        response.put("message", "Category updated successfully");
        return response;
        // update the category by id
    }


    // add annotation to call this method for a DELETE action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HashMap<String, String> deleteCategory(@PathVariable int id){
        categoryDao.delete(id);
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "Successful");
        response.put("message", "Cat deleted successfully");
        return response;
        // delete the category by id
    }
}
