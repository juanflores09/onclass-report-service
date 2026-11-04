package com.pragma.onclass_report_service.infraestructure.adapters.persistenceadapters.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bootcamp_report")
public class BootcampReportDocument {

    @Id
    private String id;
    private Long bootcampId;
    private String name;
    private String description;
    private LocalDate launchDate;
    private Integer durationInWeeks;
    private Integer capabilityCount;
    private Integer technologyCount;
    private Long enrolledCount;
    private Instant createdAt;
}
