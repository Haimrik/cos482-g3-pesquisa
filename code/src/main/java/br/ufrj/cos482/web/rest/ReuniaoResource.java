package br.ufrj.cos482.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.ufrj.cos482.service.ReuniaoService;
import br.ufrj.cos482.web.rest.util.HeaderUtil;
import br.ufrj.cos482.web.rest.util.PaginationUtil;
import br.ufrj.cos482.service.dto.ReuniaoDTO;
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
 * REST controller for managing Reuniao.
 */
@RestController
@RequestMapping("/api")
public class ReuniaoResource {

    private final Logger log = LoggerFactory.getLogger(ReuniaoResource.class);

    private static final String ENTITY_NAME = "reuniao";

    private final ReuniaoService reuniaoService;

    public ReuniaoResource(ReuniaoService reuniaoService) {
        this.reuniaoService = reuniaoService;
    }

    /**
     * POST  /reuniaos : Create a new reuniao.
     *
     * @param reuniaoDTO the reuniaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reuniaoDTO, or with status 400 (Bad Request) if the reuniao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/reuniaos")
    @Timed
    public ResponseEntity<ReuniaoDTO> createReuniao(@RequestBody ReuniaoDTO reuniaoDTO) throws URISyntaxException {
        log.debug("REST request to save Reuniao : {}", reuniaoDTO);
        if (reuniaoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new reuniao cannot already have an ID")).body(null);
        }
        ReuniaoDTO result = reuniaoService.save(reuniaoDTO);
        return ResponseEntity.created(new URI("/api/reuniaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reuniaos : Updates an existing reuniao.
     *
     * @param reuniaoDTO the reuniaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reuniaoDTO,
     * or with status 400 (Bad Request) if the reuniaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the reuniaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/reuniaos")
    @Timed
    public ResponseEntity<ReuniaoDTO> updateReuniao(@RequestBody ReuniaoDTO reuniaoDTO) throws URISyntaxException {
        log.debug("REST request to update Reuniao : {}", reuniaoDTO);
        if (reuniaoDTO.getId() == null) {
            return createReuniao(reuniaoDTO);
        }
        ReuniaoDTO result = reuniaoService.save(reuniaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reuniaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reuniaos : get all the reuniaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of reuniaos in body
     */
    @GetMapping("/reuniaos")
    @Timed
    public ResponseEntity<List<ReuniaoDTO>> getAllReuniaos(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Reuniaos");
        Page<ReuniaoDTO> page = reuniaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reuniaos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /reuniaos/:id : get the "id" reuniao.
     *
     * @param id the id of the reuniaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reuniaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/reuniaos/{id}")
    @Timed
    public ResponseEntity<ReuniaoDTO> getReuniao(@PathVariable Long id) {
        log.debug("REST request to get Reuniao : {}", id);
        ReuniaoDTO reuniaoDTO = reuniaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(reuniaoDTO));
    }

    /**
     * DELETE  /reuniaos/:id : delete the "id" reuniao.
     *
     * @param id the id of the reuniaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/reuniaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteReuniao(@PathVariable Long id) {
        log.debug("REST request to delete Reuniao : {}", id);
        reuniaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
