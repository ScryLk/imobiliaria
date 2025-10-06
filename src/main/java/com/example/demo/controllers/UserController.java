package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.UserDTO;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;
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
@RequestMapping(value = "/users")
public class UserController {
   
    @Autowired
    private UserService service;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserModel> listaNormal = service.getAll();
        List<UserDTO> listaDtos = listaNormal.stream().map(usuario -> new UserDTO(usuario)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(listaDtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        UserModel user = service.find(id);
        if(user!=null) {
            UserDTO dto = new UserDTO(user);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> update(@RequestBody UserModel model, @PathVariable int id) {
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
    public ResponseEntity<UserModel> createUser(@RequestBody UserDTO dto) {
        UserModel savedUser = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        //trocar o retorno para url
}
}