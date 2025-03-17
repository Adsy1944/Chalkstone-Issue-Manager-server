package com.chalkstone.issue_management.controller;

import com.chalkstone.issue_management.model.Category;
import com.chalkstone.issue_management.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping(path="/category")
public class CategoryController {

    private final CategoryService categoryService;
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    ObjectMapper mapper = new ObjectMapper();

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path="/getAllCategories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCategories() {
        try {
            logger.info("Gettting all categories");
            List<Category> response = categoryService.getAllCategories();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            logger.error("Could not get all categories\n{}", e.getMessage());
            return ResponseEntity.badRequest().body("Could not get all categories");
        }
    }

    @GetMapping(path="/getCategoryById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
        try {
            logger.info("Getting category by id: {}", id);
            Optional<Category> response = categoryService.getCategoryById(id);
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            logger.error("Could not get category for id: {}\n{}", id, e.getMessage());
            return ResponseEntity.badRequest().body("Could not get all employees");
        }
    }

    @PostMapping(path="/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        try {
            String json = mapper.writeValueAsString(category);
            logger.info("Adding category:\n{}", json);
            Category response = categoryService.addCategory(category);
            return ResponseEntity.ok().body(category);
        } catch (Exception e) {
            logger.info("Could not add category\n{}", e.getMessage());
            return ResponseEntity.badRequest().body("Could not add category");
        }
    }
}
