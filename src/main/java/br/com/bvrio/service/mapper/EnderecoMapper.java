package br.com.bvrio.service.mapper;

import br.com.bvrio.domain.*;
import br.com.bvrio.service.dto.EnderecoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Endereco and its DTO EnderecoDTO.
 */
@Mapper(componentModel = "spring", uses = {EstadoMapper.class, })
public interface EnderecoMapper extends EntityMapper <EnderecoDTO, Endereco> {

    @Mapping(source = "estado", target = "estado")
    EnderecoDTO toDto(Endereco endereco);
    @Mapping(target = "cliente", ignore = true)

    @Mapping(source = "estado", target = "estado")
    Endereco toEntity(EnderecoDTO enderecoDTO);
    default Endereco fromId(Long id) {
        if (id == null) {
            return null;
        }
        Endereco endereco = new Endereco();
        endereco.setId(id);
        return endereco;
    }
}
