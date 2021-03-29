package com.artemzi.cores.rest.request;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data
class EmployeeDto {
    @Nullable
    private Long id;
    private String name;
    private Long salary;
}
