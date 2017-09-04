package br.com.bvrio.service.mapper;

import br.com.bvrio.domain.*;
import br.com.bvrio.service.dto.EstadoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Estado and its DTO EstadoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EstadoMapper extends EntityMapper <EstadoDTO, Estado> {
    
    @Mapping(target = "enderecos", ignore = true)
    Estado toEntity(EstadoDTO estadoDTO); 
    default Estado fromId(Long id) {
        if (id == null) {
            return null;
        }
        Estado estado = new Estado();
        estado.setId(id);
        return estado;
    }
}
