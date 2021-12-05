package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.models.Administrador;
import com.example.demo.repositorio.AdministradoresRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministradoresController {

  @Autowired
  private AdministradoresRepo repo;

  @GetMapping("/administradores.json")
  public List<Administrador> index(){
    List<Administrador> administradores = (List<Administrador>)repo.findAll();
    return administradores;
  }

  @PostMapping("/administradores.json")
  public Administrador criar(@RequestBody Administrador administrador){
    repo.save(administrador);
    return administrador;
  }

  @GetMapping("/administradores/{id}.json")
  public Administrador busca(@PathVariable int id, HttpServletResponse response){
    if(!repo.existsById(id)){
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return new Administrador();
    }

    Optional<Administrador> admin = repo.findById(id);
    return admin.get();
  }

  @PutMapping("/administradores/{id}.json")
  public ResponseEntity<Administrador> atualizar(@PathVariable int id, HttpServletResponse response, @RequestBody Administrador administrador){
    if(!repo.existsById(id)){
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return ResponseEntity.status(404).build();
    }

    administrador.setId(id);
    repo.save(administrador);
    return ResponseEntity.ok(administrador);
  }


  @DeleteMapping("/administradores/{id}.json")
  public ResponseEntity<Administrador> excluir(@PathVariable int id, HttpServletResponse response){
      
    if(!repo.existsById(id)){
      return ResponseEntity.status(404).build();
    }

    repo.deleteById(id);
    return null;
  }
}
