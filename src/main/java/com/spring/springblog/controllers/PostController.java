package com.spring.springblog.controllers;

import com.spring.springblog.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String postIndex(Model model){
        Post post1 = new Post("First post", "this is my first post", 1);
        Post post2 = new Post("Another post", "this is some other post", 2);
        List<Post> postList = new ArrayList<>();
        postList.add(post1);
        postList.add(post2);
        model.addAttribute("title", "All Posts");
        model.addAttribute("posts", postList);
        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String postView(Model model){
        Post post = new Post("Individual post", "This is a individual post", 1);
        model.addAttribute("title", "Single Posts");
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
}
