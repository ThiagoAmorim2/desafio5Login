package com.microservice.login.dto;

import javax.validation.constraints.NotBlank;

public class PaginacaoRequestDTO {
    
    @NotBlank(message = "Não pode ser nulo")
    private Integer limit;

    @NotBlank(message = "Não pode ser nulo")
    private Integer offset;

    @NotBlank(message = "Não pode ser nulo")
    private String sort;


    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }


}
