package br.ufrj.cos482.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.ufrj.cos482.service.SeminarioService;
import br.ufrj.cos482.web.rest.util.HeaderUtil;
import br.ufrj.cos482.web.rest.util.PaginationUtil;
import br.ufrj.cos482.service.dto.SeminarioDTO;
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
 * REST controller for managing Seminario.
 */
@RestController
@RequestMapping("/api")
public class SeminarioResource {

    private final Logger log = LoggerFactory.getLogger(SeminarioResource.class);

    private static final String ENTITY_NAME = "seminario";

    private final SeminarioService seminarioService;

    public SeminarioResource(SeminarioService seminarioService) {
        this.seminarioService = seminarioService;
    }

    /**
     * POST  /seminarios : Create a new seminario.
     *
     * @param seminarioDTO the seminarioDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new seminarioDTO, or with status 400 (Bad Request) if the seminario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/seminarios")
    @Timed
    public ResponseEntity<SeminarioDTO> createSeminario(@RequestBody SeminarioDTO seminarioDTO) throws URISyntaxException {
        log.debug("REST request to save Seminario : {}", seminarioDTO);
        if (seminarioDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new seminario cannot already have an ID")).body(null);
        }
        SeminarioDTO result = seminarioService.save(seminarioDTO);
        return ResponseEntity.created(new URI("/api/seminarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /seminarios : Updates an existing seminario.
     *
     * @param seminarioDTO the seminarioDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated seminarioDTO,
     * or with status 400 (Bad Request) if the seminarioDTO is not valid,
     * or with status 500 (Internal Server Error) if the seminarioDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/seminarios")
    @Timed
    public ResponseEntity<SeminarioDTO> updateSeminario(@RequestBody SeminarioDTO seminarioDTO) throws URISyntaxException {
        log.debug("REST request to update Seminario : {}", seminarioDTO);
        if (seminarioDTO.getId() == null) {
            return createSeminario(seminarioDTO);
        }
        SeminarioDTO result = seminarioService.save(seminarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, seminarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /seminarios : get all the seminarios.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of seminarios in body
     */
    @GetMapping("/seminarios")
    @Timed
    public ResponseEntity<List<SeminarioDTO>> getAllSeminarios(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Seminarios");
        Page<SeminarioDTO> page = seminarioService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/seminarios");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /seminarios/:id : get the "id" seminario.
     *
     * @param id the id of the seminarioDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the seminarioDTO, or with status 404 (Not Found)
     */
    @GetMapping("/seminarios/{id}")
    @Timed
    public ResponseEntity<SeminarioDTO> getSeminario(@PathVariable Long id) {
        log.debug("REST request to get Seminario : {}", id);
        SeminarioDTO seminarioDTO = seminarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(seminarioDTO));
    }

    /**
     * DELETE  /seminarios/:id : delete the "id" seminario.
     *
     * @param id the id of the seminarioDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/seminarios/{id}")
    @Timed
    public ResponseEntity<Void> deleteSeminario(@PathVariable Long id) {
        log.debug("REST request to delete Seminario : {}", id);
        seminarioService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
