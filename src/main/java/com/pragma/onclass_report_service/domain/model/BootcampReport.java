package com.pragma.onclass_report_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BootcampReport {

    private String id;
    private Long bootcampId;
    private String name;
    private String description;
    private Integer capabilityCount;
    private Integer technologyCount;
    private Long enrolledCount;
    private Instant createdAt;
}
