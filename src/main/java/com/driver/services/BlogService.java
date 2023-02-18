package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    @Autowired
    ImageRepository imageRepository;

    public List<Blog> showBlogs(){
        //find all blogs
        List<Blog> blogList = blogRepository1.findAll();
        return blogList;
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time

        //updating the blog details

        //Updating the userInformation and changing its blogs
        Blog blog = new Blog();
        blog.setContent(content);
        blog.setTitle(title);
        blog.setPubDate(new Date());

        User user = userRepository1.findById(userId).get();//get user
        blog.setUser(user);//unidirectional mapping for blog

        List<Blog> blogList = user.getBlogList();//bidirectional mapping in userEntity
        blogList.add(blog);
        user.setBlogList(blogList);
        userRepository1.save(user);
//Only calling the parent userRepository function as the child function will automatically be called by cascading
//        blogRepository1.save(blog);

    }

    public Blog findBlogById(int blogId){
        //find a blog
        Blog blog = blogRepository1.findById(blogId).get();
        return blog;
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
//        Image image = new Image();
//        Blog blog = blogRepository1.findById(blogId).get();
//        image.setBlog(blog);
//        image.setDimensions(dimensions);
//        image.setDescription(description);
//
//        List<Image> imageList = blog.getImageList();
//        imageList.add(image);
//        blog.setImageList(imageList);
//
//        blogRepository1.save(blog);//As saved in parent image need not to be saved in image Repository;
        Blog blog = blogRepository1.findById(blogId).get();
        Image image = imageService1.createAndReturn(blog,description,dimensions);
        List<Image> imageList = blog.getImageList();
        imageList.add(image);
        blog.setImageList(imageList);
        blogRepository1.save(blog);//Just calling the parent repository and child repository will automatically be called.
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Blog blog = blogRepository1.findById(blogId).get();
        if(blog!=null){
            blogRepository1.delete(blog);
        }
    }
}