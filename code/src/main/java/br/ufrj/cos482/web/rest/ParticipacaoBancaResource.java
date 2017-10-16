package br.ufrj.cos482.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.ufrj.cos482.service.ParticipacaoBancaService;
import br.ufrj.cos482.web.rest.util.HeaderUtil;
import br.ufrj.cos482.web.rest.util.PaginationUtil;
import br.ufrj.cos482.service.dto.ParticipacaoBancaDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ParticipacaoBanca.
 */
@RestController
@RequestMapping("/api")
public class ParticipacaoBancaResource {

    private final Logger log = LoggerFactory.getLogger(ParticipacaoBancaResource.class);

    private static final String ENTITY_NAME = "participacaoBanca";

    private final ParticipacaoBancaService participacaoBancaService;

    public ParticipacaoBancaResource(ParticipacaoBancaService participacaoBancaService) {
        this.participacaoBancaService = participacaoBancaService;
    }

    /**
     * POST  /participacao-bancas : Create a new participacaoBanca.
     *
     * @param participacaoBancaDTO the participacaoBancaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new participacaoBancaDTO, or with status 400 (Bad Request) if the participacaoBanca has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/participacao-bancas")
    @Timed
    public ResponseEntity<ParticipacaoBancaDTO> createParticipacaoBanca(@RequestBody ParticipacaoBancaDTO participacaoBancaDTO) throws URISyntaxException {
        log.debug("REST request to save ParticipacaoBanca : {}", participacaoBancaDTO);
        if (participacaoBancaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new participacaoBanca cannot already have an ID")).body(null);
        }
        ParticipacaoBancaDTO result = participacaoBancaService.save(participacaoBancaDTO);
        return ResponseEntity.created(new URI("/api/participacao-bancas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /participacao-bancas : Updates an existing participacaoBanca.
     *
     * @param participacaoBancaDTO the participacaoBancaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated participacaoBancaDTO,
     * or with status 400 (Bad Request) if the participacaoBancaDTO is not valid,
     * or with status 500 (Internal Server Error) if the participacaoBancaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/participacao-bancas")
    @Timed
    public ResponseEntity<ParticipacaoBancaDTO> updateParticipacaoBanca(@RequestBody ParticipacaoBancaDTO participacaoBancaDTO) throws URISyntaxException {
        log.debug("REST request to update ParticipacaoBanca : {}", participacaoBancaDTO);
        if (participacaoBancaDTO.getId() == null) {
            return createParticipacaoBanca(participacaoBancaDTO);
        }
        ParticipacaoBancaDTO result = participacaoBancaService.save(participacaoBancaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, participacaoBancaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /participacao-bancas : get all the participacaoBancas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of participacaoBancas in body
     */
    @GetMapping("/participacao-bancas")
    @Timed
    public ResponseEntity<List<ParticipacaoBancaDTO>> getAllParticipacaoBancas(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of ParticipacaoBancas");
        Page<ParticipacaoBancaDTO> page = participacaoBancaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/participacao-bancas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /participacao-bancas/:id : get the "id" participacaoBanca.
     *
     * @param id the id of the participacaoBancaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the participacaoBancaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/participacao-bancas/{id}")
    @Timed
    public ResponseEntity<ParticipacaoBancaDTO> getParticipacaoBanca(@PathVariable Long id) {
        log.debug("REST request to get ParticipacaoBanca : {}", id);
        ParticipacaoBancaDTO participacaoBancaDTO = participacaoBancaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(participacaoBancaDTO));
    }

    /**
     * DELETE  /participacao-bancas/:id : delete the "id" participacaoBanca.
     *
     * @param id the id of the participacaoBancaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/participacao-bancas/{id}")
    @Timed
    public ResponseEntity<Void> deleteParticipacaoBanca(@PathVariable Long id) {
        log.debug("REST request to delete ParticipacaoBanca : {}", id);
        participacaoBancaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
