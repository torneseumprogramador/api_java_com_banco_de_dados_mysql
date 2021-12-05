package com.example.demo.controllers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model_view.Home;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @GetMapping("/")
  public Home index(Model model, HttpServletRequest request) throws UnsupportedEncodingException{
    return new Home();
  }
}
