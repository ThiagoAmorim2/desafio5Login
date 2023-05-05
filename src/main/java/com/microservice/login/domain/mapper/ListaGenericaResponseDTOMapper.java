package com.microservice.login.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.login.dto.ListaGenericaResponseDTO;
import com.microservice.login.dto.PaginacaoResponseDTO;

@Component
public class ListaGenericaResponseDTOMapper {
    
    @Autowired
    private ObjectMapper mapper;

    public <T, U> ListaGenericaResponseDTO<T> fromPage(Page<U> objectFrom, Class<T> objectTo) {
        List<T> content = getContentResponse(objectFrom, objectTo);
        PaginacaoResponseDTO pageable = getPageableResponse(objectFrom);
        return new ListaGenericaResponseDTO<T>(pageable, content);
    }

    private <U> PaginacaoResponseDTO getPageableResponse(Page<U> objectFrom) {
        return PaginacaoResponseDTO.builder()
                .limit(objectFrom.getPageable().getPageSize())
                .offset(objectFrom.getPageable().getOffset())
                .pageNumber(objectFrom.getNumber() + 1)
                .pageElements(objectFrom.getNumberOfElements())
                .totalPages(objectFrom.getTotalPages())
                .totalElements(objectFrom.getTotalElements())
                .build();
    }

    private <T, U> List<T> getContentResponse(Page<U> objectFrom, Class<T> objectTo) {
        return objectFrom.getContent()
                .stream()
                .map(i -> mapper.convertValue(i, objectTo))
                .collect(Collectors.toList());
    }
}
