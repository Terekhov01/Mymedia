package com.example.mymedia.controller;

import com.example.mymedia.domain.Media;
import com.example.mymedia.repos.MediaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private MediaRepo mediaRepo;

    @GetMapping("/")
    public String hello(Map<String, Object> model){ return "hello";}

    @PostMapping("/library{id}")
    public String delete(Model model, @PathVariable Integer id){
        Optional<Media> media = mediaRepo.findById(id);
        if(media.isPresent()) {
            mediaRepo.delete(media.get());
            model.addAttribute("mediaList", mediaRepo.findAll());
        }
        return "redirect:/library";
    }

    @GetMapping("/library")
    public String library(Model model){
        Iterable<Media> mediaList = mediaRepo.findAll();

        model.addAttribute("mediaList",mediaList);

        return "library";
    }

    @PostMapping("/library")
    public String add(Model model,
                      @RequestParam String name,
                      @RequestParam String date){
        Media media = new Media(name, date);

        mediaRepo.save(media);

        Iterable<Media> mediaList = mediaRepo.findAll();

        model.addAttribute("mediaList", mediaList);

        return "library";
    }

    @PostMapping("/library/edit{id}")
    public String updateMedia(Model model,
                              @PathVariable Integer id,
                              @RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "") String date){
        Optional<Media> media = mediaRepo.findById(id);
        if(media.isPresent()) {
            if(!name.equals(""))
                media.get().setName(name);
            if(!date.equals(""))
                media.get().setDate(date);
            mediaRepo.save(media.get());
            model.addAttribute("mediaList", mediaRepo.findAll());
        }
        return "redirect:/library";
    }
}
