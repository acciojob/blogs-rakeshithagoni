package com.driver.controller;

import com.driver.models.Blog;
import com.driver.repositories.BlogRepository;
import com.driver.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    BlogRepository blogRepository;

    @GetMapping("/get_no_of_blogs")
    public ResponseEntity<Integer> getAllBlogs() {
        List<Blog> totalBlogs=blogRepository.findAll();
        int countOfBlogs = totalBlogs.size();
        return new ResponseEntity<>(countOfBlogs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity createBlog(@RequestParam Integer userId ,
                                     @RequestParam String title,
                                     @RequestParam String content) {

        blogService.createAndReturnBlog(userId, title, content);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{blogId}/add-image")
    public ResponseEntity<String> addImage(@PathVariable int blogId, @RequestParam String description, @RequestParam String dimensions) {
        blogService.addImage(blogId, description, dimensions);
        return new ResponseEntity<>("Added image successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable int blogId) {
        blogService.deleteBlog(blogId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

