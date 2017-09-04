package br.com.bvrio.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.bvrio.domain.Estado;

import br.com.bvrio.repository.EstadoRepository;
import br.com.bvrio.web.rest.util.HeaderUtil;
import br.com.bvrio.service.dto.EstadoDTO;
import br.com.bvrio.service.mapper.EstadoMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Estado.
 */
@RestController
@RequestMapping("/api")
public class EstadoResource {

    private final Logger log = LoggerFactory.getLogger(EstadoResource.class);

    private static final String ENTITY_NAME = "estado";

    private final EstadoRepository estadoRepository;

    private final EstadoMapper estadoMapper;

    public EstadoResource(EstadoRepository estadoRepository, EstadoMapper estadoMapper) {
        this.estadoRepository = estadoRepository;
        this.estadoMapper = estadoMapper;
    }

    /**
     * POST  /estados : Create a new estado.
     *
     * @param estadoDTO the estadoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new estadoDTO, or with status 400 (Bad Request) if the estado has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/estados")
    @Timed
    public ResponseEntity<EstadoDTO> createEstado(@RequestBody EstadoDTO estadoDTO) throws URISyntaxException {
        log.debug("REST request to save Estado : {}", estadoDTO);
        if (estadoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new estado cannot already have an ID")).body(null);
        }
        Estado estado = estadoMapper.toEntity(estadoDTO);
        estado = estadoRepository.save(estado);
        EstadoDTO result = estadoMapper.toDto(estado);
        return ResponseEntity.created(new URI("/api/estados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /estados : Updates an existing estado.
     *
     * @param estadoDTO the estadoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated estadoDTO,
     * or with status 400 (Bad Request) if the estadoDTO is not valid,
     * or with status 500 (Internal Server Error) if the estadoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/estados")
    @Timed
    public ResponseEntity<EstadoDTO> updateEstado(@RequestBody EstadoDTO estadoDTO) throws URISyntaxException {
        log.debug("REST request to update Estado : {}", estadoDTO);
        if (estadoDTO.getId() == null) {
            return createEstado(estadoDTO);
        }
        Estado estado = estadoMapper.toEntity(estadoDTO);
        estado = estadoRepository.save(estado);
        EstadoDTO result = estadoMapper.toDto(estado);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, estadoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /estados : get all the estados.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of estados in body
     */
    @GetMapping("/estados")
    @Timed
    public List<EstadoDTO> getAllEstados() {
        log.debug("REST request to get all Estados");
        List<Estado> estados = estadoRepository.findAll();
        return estadoMapper.toDto(estados);
    }

    /**
     * GET  /estados/:id : get the "id" estado.
     *
     * @param id the id of the estadoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the estadoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/estados/{id}")
    @Timed
    public ResponseEntity<EstadoDTO> getEstado(@PathVariable Long id) {
        log.debug("REST request to get Estado : {}", id);
        Estado estado = estadoRepository.findOne(id);
        EstadoDTO estadoDTO = estadoMapper.toDto(estado);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(estadoDTO));
    }

    /**
     * DELETE  /estados/:id : delete the "id" estado.
     *
     * @param id the id of the estadoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/estados/{id}")
    @Timed
    public ResponseEntity<Void> deleteEstado(@PathVariable Long id) {
        log.debug("REST request to delete Estado : {}", id);
        estadoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
