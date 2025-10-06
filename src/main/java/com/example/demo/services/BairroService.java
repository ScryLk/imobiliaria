package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.UserDTO;
import com.example.demo.models.BairroModel;
import com.example.demo.repositories.BairroRepository;
import com.example.demo.repositories.UserRepository;


@Service
public class BairroService {
    
    @Autowired
    BairroRepository repositorio;

    //http -> Controller -> Service (getAll()) -> Repository -> Banco
    public List<BairroModel> getAll() {
        List<BairroModel> lista = repositorio.findAll();
        return lista;
    }

    public BairroModel find(Integer id) {
        Optional<BairroModel> model = repositorio.findById(id);
        return model.orElse(null);
    }

    public BairroModel insert(BairroModel user) {
        return repositorio.save(user);
    }

    public BairroModel insert(UserDTO dto) {
        BairroModel model = new BairroModel();
        model.setNome(model.getNome());
        model.setCidade(model.getCidade());
        model.setEstado(model.getEstado());
        model.setCep_inicial(model.getCep_inicial());
        model.setCep_final(model.getCep_final());
        model.setCreated_at(model.getCreated_at());
        model.setUpdated_at(model.getUpdated_at());
        //Trabalhos Futuros -> Criar a senha criptografada
        return repositorio.save(model);
    }

    public BairroModel update(BairroModel user) {
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
