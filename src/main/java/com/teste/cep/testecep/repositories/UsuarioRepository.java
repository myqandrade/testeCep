package com.teste.cep.testecep.repositories;

import com.teste.cep.testecep.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
