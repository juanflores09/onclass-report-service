package com.pragma.onclass_report_service.infraestructure.entrypoints.dto;

public record BootcampReportRequestDTO (
        Long bootcampId,
        String name,
        String description,
        Integer capabilityCount,
        Integer technologyCount
){
}
