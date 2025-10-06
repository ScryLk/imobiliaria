package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.UserDTO;
import com.example.demo.models.BairroModel;
import com.example.demo.models.BairroModel;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.BairroService;
import com.example.demo.services.UserService;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/bairros")
public class BairroController {
   
    @Autowired
    private BairroService service;

    @GetMapping()
    public ResponseEntity<List> getAllUsers() {
        List<BairroModel> listaNormal = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(listaNormal);

    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable int id) {
        BairroModel bairro = service.find(id);
        if(bairro!=null) {
            
            return ResponseEntity.status(HttpStatus.OK).body(bairro);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BairroModel> update(@RequestBody BairroModel model, @PathVariable int id) {
        model.setId(id);
        model = service.update(model);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
        
    }
    
    @PostMapping()
    public ResponseEntity<BairroModel> createBairro(@RequestBody BairroModel model) {
        BairroModel savedBairro = service.insert(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBairro);
        //trocar o retorno para url
}
}