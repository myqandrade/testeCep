package com.teste.cep.testecep.repositories;

import com.teste.cep.testecep.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
