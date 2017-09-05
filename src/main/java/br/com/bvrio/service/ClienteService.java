package br.com.bvrio.service;

import br.com.bvrio.domain.Cliente;
import br.com.bvrio.repository.ClienteRepository;
import br.com.bvrio.web.rest.errors.CustomParameterizedException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by Avell 1513 on 04/09/2017.
 */
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public Cliente save(Cliente cliente) throws CustomParameterizedException {
        Cliente toReturn = clienteRepository.findByFilter(cliente.getEmail());

        if(Objects.nonNull(toReturn)){
            throw new CustomParameterizedException("O email "+cliente.getEmail()+" já está sendo utilizado. Por favor, informe outro email.");
        }


        return clienteRepository.save(cliente);
    }
}
