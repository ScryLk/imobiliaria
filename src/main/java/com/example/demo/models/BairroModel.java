package com.example.demo.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bairros")
@Getter
@Setter
public class BairroModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String cidade;

    private String estado;

    private String cep_inicial;

    private String cep_final;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    public BairroModel(){};


    public BairroModel(int id, String nome, String cidade, String estado, String cep_inicial, String cep_final, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.cep_inicial = cep_inicial;
        this.cep_final = cep_final;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BairroModel other = (BairroModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    //JPA (Java Persistance API)

}
