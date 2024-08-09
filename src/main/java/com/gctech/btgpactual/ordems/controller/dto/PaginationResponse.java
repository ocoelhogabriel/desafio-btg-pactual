package com.gctech.btgpactual.ordems.controller.dto;

import org.springframework.data.domain.Page;

public record PaginationResponse(Integer page, Integer PageSize, Integer totalElements, Integer totalPages) {

    public static PaginationResponse fromPage(Page<?> page) {
        return new PaginationResponse(
                page.getNumber(), page.getSize(), page.getNumberOfElements(), page.getTotalPages()
        );
    }
}
