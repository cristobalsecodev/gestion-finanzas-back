package com.gestionFinanzas.Shared.DTOs;

import lombok.Data;

@Data
public class PaginationData {

    private Integer page = 0;
    private Integer size = 10;
    private String sortDir = "desc";

}
