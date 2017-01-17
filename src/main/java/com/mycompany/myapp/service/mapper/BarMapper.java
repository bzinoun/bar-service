package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.BarDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Bar and its DTO BarDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BarMapper {

    BarDTO barToBarDTO(Bar bar);

    List<BarDTO> barsToBarDTOs(List<Bar> bars);

    Bar barDTOToBar(BarDTO barDTO);

    List<Bar> barDTOsToBars(List<BarDTO> barDTOs);
}
