package com.teste.cep.testecep.controllers;

import com.google.gson.Gson;
import com.teste.cep.testecep.entities.Usuario;
import com.teste.cep.testecep.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/cep")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @GetMapping
    @ResponseBody
    public ResponseEntity getCep(@RequestParam(name = "id") Integer id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        try{
            String uri = "https://viacep.com.br/ws/" + usuario.get().getCep() + "/json/";
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            String cepJson = new Gson().toJson(result);
            return new ResponseEntity(cepJson, OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity("Error!, please try again", INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity salvarUsuario(@RequestBody Usuario usuario){
        return new ResponseEntity(usuarioRepository.save(usuario), CREATED);
    }
}
