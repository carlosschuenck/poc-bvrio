package br.com.bvrio.service.mapper;

import br.com.bvrio.domain.*;
import br.com.bvrio.service.dto.ClienteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Cliente and its DTO ClienteDTO.
 */
@Mapper(componentModel = "spring", uses = {EnderecoMapper.class, })
public interface ClienteMapper extends EntityMapper <ClienteDTO, Cliente> {

    @Mapping(source = "endereco", target = "endereco")
    ClienteDTO toDto(Cliente cliente);

    @Mapping(source = "endereco", target = "endereco")
    Cliente toEntity(ClienteDTO clienteDTO);
    default Cliente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setId(id);
        return cliente;
    }
}
