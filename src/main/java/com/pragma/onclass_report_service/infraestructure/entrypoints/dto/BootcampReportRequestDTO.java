package com.pragma.onclass_report_service.infraestructure.entrypoints.dto;

import java.time.LocalDate;

public record BootcampReportRequestDTO (
        Long bootcampId,
        String name,
        String description,
        LocalDate launchDate,
        Integer durationInWeeks,
        Integer capabilityCount,
        Integer technologyCount
){
}
