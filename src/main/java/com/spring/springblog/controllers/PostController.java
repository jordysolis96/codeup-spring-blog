package com.spring.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public String post() {
        return "posts index page";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String postById() {
        return "view an individual post";
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
