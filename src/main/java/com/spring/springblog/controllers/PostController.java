package com.spring.springblog.controllers;

import com.spring.springblog.model.Post;
import com.spring.springblog.repositiories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }



    @RequestMapping(path = "/posts")
    public String postIndex(Model model){

        List<Post> postList = postDao.findAll();

       model.addAttribute("title", "All Posts");
       model.addAttribute("posts", postList);

        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String postView(Model model, @PathVariable long id){
        Post post = postDao.getOne(id);
        model.addAttribute("post", post);
        return "posts/show";
    }


    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String CreatePostForm() {
        return "view the form for creating a post";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String CreatePost() {
        return "create a new post";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEditPostForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
        Post post = new Post(
                body,
                title,
                id
        );
        postDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        System.out.println("Deleting post...");
        postDao.deleteById(id);
        return "redirect:/posts";
    }
}
