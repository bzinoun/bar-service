package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.BarDTO;
import java.util.List;

/**
 * Service Interface for managing Bar.
 */
public interface BarService {

    /**
     * Save a bar.
     *
     * @param barDTO the entity to save
     * @return the persisted entity
     */
    BarDTO save(BarDTO barDTO);

    /**
     *  Get all the bars.
     *  
     *  @return the list of entities
     */
    List<BarDTO> findAll();

    /**
     *  Get the "id" bar.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    BarDTO findOne(Long id);

    /**
     *  Delete the "id" bar.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
