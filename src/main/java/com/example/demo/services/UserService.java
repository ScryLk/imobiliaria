package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.UserDTO;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository repositorio;

    //http -> Controller -> Service (getAll()) -> Repository -> Banco
    public List<UserModel> getAll() {
        List<UserModel> lista = repositorio.findAll();
        return lista;
    }

    public UserModel find(Integer id) {
        Optional<UserModel> model = repositorio.findById(id);
        return model.orElse(null);
    }

    public UserModel insert(UserModel user) {
        return repositorio.save(user);
    }

    public UserModel insert(UserDTO dto) {
        UserModel model = new UserModel();
        model.setEmail(dto.getEmail());
        model.setNome(dto.getNome());
        model.setTipo(dto.getTipo());
        //Trabalhos Futuros -> Criar a senha criptografada
        return repositorio.save(model);
    }

    public UserModel update(UserModel user) {
        try {
            if(find(user.getId()) != null) {
                return repositorio.save(user);
            }
            return null;
        } catch (Exception e) {
         return null;
        }
    }

    public void delete(Integer id) {
        repositorio.deleteById(id);
        
    }

}
