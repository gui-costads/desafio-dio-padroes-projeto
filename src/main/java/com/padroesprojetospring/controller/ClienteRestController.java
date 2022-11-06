package com.padroesprojetospring.controller;

import com.padroesprojetospring.model.Cliente;
import com.padroesprojetospring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired(required = true)
    private ClienteService clienteService;


    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos(){

        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarId(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.buscarPorID(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente){
        clienteService.inserir(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente){
        clienteService.atualizar(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deletar(@PathVariable Long id, @RequestBody Cliente cliente){
        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }

}
