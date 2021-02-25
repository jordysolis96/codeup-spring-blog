package com.spring.springblog.controllers;

import com.spring.springblog.model.Post;
import com.spring.springblog.model.User;
import com.spring.springblog.repositories.PostRepository;
import com.spring.springblog.repositories.UserRepository;
import com.spring.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postsDao;

    private final UserRepository userDao;

    private final EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository userDao, EmailService emailService){
        this.postsDao = postsDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String postsIndex(Model model) {

        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postView(Model model, @PathVariable long id) {

        Post post = postsDao.getOne(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEditPostForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", postsDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post) {

        User user = userDao.findAll().get(0);
        post.setUser(user);

        postsDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        System.out.println("Deleting post...");
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String postForm(Model model){

        model.addAttribute("post", new Post());

        Post savedPost = postsDao.save(post);
        String subject = "New Ad Created";
        String body = "Dear " + savedPost.getUser().getUsername() + ". Thank you for creating an Ad. Your ad is: " + savedPost.getId();

        emailService.prepareAndSend(savedPost, subject, body);

        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {

        User user = userDao.findAll().get(0);
        post.setUser(user);

//        Post savedPost = postsDao.save(post);
//        String subject = "New Ad Created";
//        String body = "Dear " + savedPost.getUser().getUsername() + ". Thank you for creating an Ad. Your ad is: " + savedPost.getId();

//        emailService.prepareAndSend(savedPost, subject, body);

        postsDao.save(post);
        return "redirect:/posts/" + post.getId();
    }
}
