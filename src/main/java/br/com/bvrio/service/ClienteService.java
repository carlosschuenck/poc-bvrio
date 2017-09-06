package br.com.bvrio.service;

import br.com.bvrio.domain.Cliente;
import br.com.bvrio.repository.ClienteRepository;
import br.com.bvrio.web.rest.errors.CustomParameterizedException;
import static org.apache.commons.lang3.StringUtils.*;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import static java.util.Objects.*;

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

        if(isBlank(cliente.getEmail())){
            throw new CustomParameterizedException("O email é obrigatório.");
        }

        if(nonNull(clienteRepository.findByFilter(cliente.getEmail()))){
            throw new CustomParameterizedException("O email "+cliente.getEmail()+" já está sendo utilizado por outro cliente. Por favor, informe outro email.");
        }

        if(isBlank(cliente.getNome())){
            throw new CustomParameterizedException("O nome é obrigatório.");
        }

        if(isBlank(cliente.getEndereco().getCidade())){
            throw new CustomParameterizedException("A cidade é obrigatória.");
        }

        if(isBlank(cliente.getEndereco().getCep())){
            throw new CustomParameterizedException("O CEP é obrigatório.");
        }

        if(nonNull(cliente.getDataNascimento())){
            LocalDate dataNascimento = cliente.getDataNascimento().plusYears(16);
            LocalDate dataAtual = LocalDate.now();
            if((dataAtual.isEqual(dataNascimento) || dataAtual.isBefore(cliente.getDataNascimento().plusYears(16)))){
                throw new CustomParameterizedException("A idade do cliente não pode ser menor que 16 anos.");
            }
        }

        return clienteRepository.save(cliente);
    }
}
