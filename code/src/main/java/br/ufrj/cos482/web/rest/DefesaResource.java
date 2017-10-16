package br.ufrj.cos482.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.ufrj.cos482.service.DefesaService;
import br.ufrj.cos482.web.rest.util.HeaderUtil;
import br.ufrj.cos482.web.rest.util.PaginationUtil;
import br.ufrj.cos482.service.dto.DefesaDTO;
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
 * REST controller for managing Defesa.
 */
@RestController
@RequestMapping("/api")
public class DefesaResource {

    private final Logger log = LoggerFactory.getLogger(DefesaResource.class);

    private static final String ENTITY_NAME = "defesa";

    private final DefesaService defesaService;

    public DefesaResource(DefesaService defesaService) {
        this.defesaService = defesaService;
    }

    /**
     * POST  /defesas : Create a new defesa.
     *
     * @param defesaDTO the defesaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new defesaDTO, or with status 400 (Bad Request) if the defesa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/defesas")
    @Timed
    public ResponseEntity<DefesaDTO> createDefesa(@RequestBody DefesaDTO defesaDTO) throws URISyntaxException {
        log.debug("REST request to save Defesa : {}", defesaDTO);
        if (defesaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new defesa cannot already have an ID")).body(null);
        }
        DefesaDTO result = defesaService.save(defesaDTO);
        return ResponseEntity.created(new URI("/api/defesas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /defesas : Updates an existing defesa.
     *
     * @param defesaDTO the defesaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated defesaDTO,
     * or with status 400 (Bad Request) if the defesaDTO is not valid,
     * or with status 500 (Internal Server Error) if the defesaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/defesas")
    @Timed
    public ResponseEntity<DefesaDTO> updateDefesa(@RequestBody DefesaDTO defesaDTO) throws URISyntaxException {
        log.debug("REST request to update Defesa : {}", defesaDTO);
        if (defesaDTO.getId() == null) {
            return createDefesa(defesaDTO);
        }
        DefesaDTO result = defesaService.save(defesaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, defesaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /defesas : get all the defesas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of defesas in body
     */
    @GetMapping("/defesas")
    @Timed
    public ResponseEntity<List<DefesaDTO>> getAllDefesas(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Defesas");
        Page<DefesaDTO> page = defesaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/defesas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /defesas/:id : get the "id" defesa.
     *
     * @param id the id of the defesaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the defesaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/defesas/{id}")
    @Timed
    public ResponseEntity<DefesaDTO> getDefesa(@PathVariable Long id) {
        log.debug("REST request to get Defesa : {}", id);
        DefesaDTO defesaDTO = defesaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(defesaDTO));
    }

    /**
     * DELETE  /defesas/:id : delete the "id" defesa.
     *
     * @param id the id of the defesaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/defesas/{id}")
    @Timed
    public ResponseEntity<Void> deleteDefesa(@PathVariable Long id) {
        log.debug("REST request to delete Defesa : {}", id);
        defesaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
