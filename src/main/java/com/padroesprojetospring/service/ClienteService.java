package com.padroesprojetospring.service;

import com.padroesprojetospring.model.Cliente;

public interface ClienteService {


    Iterable<Cliente> buscarTodos();
    Cliente buscarPorID(Long id);
    void inserir(Cliente cliente);
    void atualizar(Long id, Cliente cliente);
    void delete(Long id);
}
