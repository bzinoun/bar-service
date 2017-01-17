package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.BarService;
import com.mycompany.myapp.domain.Bar;
import com.mycompany.myapp.repository.BarRepository;
import com.mycompany.myapp.service.dto.BarDTO;
import com.mycompany.myapp.service.mapper.BarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Bar.
 */
@Service
@Transactional
public class BarServiceImpl implements BarService{

    private final Logger log = LoggerFactory.getLogger(BarServiceImpl.class);
    
    @Inject
    private BarRepository barRepository;

    @Inject
    private BarMapper barMapper;

    /**
     * Save a bar.
     *
     * @param barDTO the entity to save
     * @return the persisted entity
     */
    public BarDTO save(BarDTO barDTO) {
        log.debug("Request to save Bar : {}", barDTO);
        Bar bar = barMapper.barDTOToBar(barDTO);
        bar = barRepository.save(bar);
        BarDTO result = barMapper.barToBarDTO(bar);
        return result;
    }

    /**
     *  Get all the bars.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<BarDTO> findAll() {
        log.debug("Request to get all Bars");
        List<BarDTO> result = barRepository.findAll().stream()
            .map(barMapper::barToBarDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one bar by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public BarDTO findOne(Long id) {
        log.debug("Request to get Bar : {}", id);
        Bar bar = barRepository.findOne(id);
        BarDTO barDTO = barMapper.barToBarDTO(bar);
        return barDTO;
    }

    /**
     *  Delete the  bar by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Bar : {}", id);
        barRepository.delete(id);
    }
}
