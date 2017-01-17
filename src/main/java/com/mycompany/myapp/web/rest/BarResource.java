package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.service.BarService;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.BarDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Bar.
 */
@RestController
@RequestMapping("/api")
public class BarResource {

    private final Logger log = LoggerFactory.getLogger(BarResource.class);
        
    @Inject
    private BarService barService;

    /**
     * POST  /bars : Create a new bar.
     *
     * @param barDTO the barDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new barDTO, or with status 400 (Bad Request) if the bar has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bars")
    @Timed
    public ResponseEntity<BarDTO> createBar(@RequestBody BarDTO barDTO) throws URISyntaxException {
        log.debug("REST request to save Bar : {}", barDTO);
        if (barDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("bar", "idexists", "A new bar cannot already have an ID")).body(null);
        }
        BarDTO result = barService.save(barDTO);
        return ResponseEntity.created(new URI("/api/bars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("bar", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bars : Updates an existing bar.
     *
     * @param barDTO the barDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated barDTO,
     * or with status 400 (Bad Request) if the barDTO is not valid,
     * or with status 500 (Internal Server Error) if the barDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bars")
    @Timed
    public ResponseEntity<BarDTO> updateBar(@RequestBody BarDTO barDTO) throws URISyntaxException {
        log.debug("REST request to update Bar : {}", barDTO);
        if (barDTO.getId() == null) {
            return createBar(barDTO);
        }
        BarDTO result = barService.save(barDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("bar", barDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bars : get all the bars.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bars in body
     */
    @GetMapping("/bars")
    @Timed
    public List<BarDTO> getAllBars() {
        log.debug("REST request to get all Bars");
        return barService.findAll();
    }

    /**
     * GET  /bars/:id : get the "id" bar.
     *
     * @param id the id of the barDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the barDTO, or with status 404 (Not Found)
     */
    @GetMapping("/bars/{id}")
    @Timed
    public ResponseEntity<BarDTO> getBar(@PathVariable Long id) {
        log.debug("REST request to get Bar : {}", id);
        BarDTO barDTO = barService.findOne(id);
        return Optional.ofNullable(barDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /bars/:id : delete the "id" bar.
     *
     * @param id the id of the barDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bars/{id}")
    @Timed
    public ResponseEntity<Void> deleteBar(@PathVariable Long id) {
        log.debug("REST request to delete Bar : {}", id);
        barService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("bar", id.toString())).build();
    }

}
