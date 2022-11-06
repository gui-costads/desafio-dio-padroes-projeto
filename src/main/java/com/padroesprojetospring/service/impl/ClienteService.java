package com.padroesprojetospring.service.impl;

import com.padroesprojetospring.model.Cliente;
import com.padroesprojetospring.model.ClienteRepository;
import com.padroesprojetospring.model.Endereco;
import com.padroesprojetospring.model.EnderecoRepository;
import com.padroesprojetospring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService implements com.padroesprojetospring.service.ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorID(Long id) {
        Optional<Cliente> clienteId = clienteRepository.findById(id);
        return clienteId.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBD = clienteRepository.findById(id);
        if (clienteBD.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
