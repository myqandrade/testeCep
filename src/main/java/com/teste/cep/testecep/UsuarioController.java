package com.teste.cep.testecep;

import com.teste.cep.testecep.entities.Usuario;
import com.teste.cep.testecep.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @PostMapping("/save")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity save(@RequestBody Usuario usuario) throws Exception {
        URL url = new URL("https://viacep.com.br/ws/"+ usuario.getCep()+ "/json/");
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String cep = "";
        StringBuilder jsonCep = new StringBuilder();
        while((cep = br.readLine()) != null) {
            jsonCep.append(cep);
        }
        System.out.println(jsonCep.toString());
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }
}
