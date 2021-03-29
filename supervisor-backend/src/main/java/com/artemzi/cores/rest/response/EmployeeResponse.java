package com.artemzi.cores.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data
class EmployeeResponse {
    private long id;
    private String name;
    private Long salary;
}
