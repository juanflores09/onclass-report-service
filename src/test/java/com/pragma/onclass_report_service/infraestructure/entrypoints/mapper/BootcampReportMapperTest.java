package com.pragma.onclass_report_service.infraestructure.entrypoints.mapper;

import com.pragma.onclass_report_service.domain.model.BootcampReport;
import com.pragma.onclass_report_service.infraestructure.adapters.persistenceadapters.entity.BootcampReportDocument;
import com.pragma.onclass_report_service.infraestructure.entrypoints.dto.BootcampReportRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class BootcampReportMapperTest {

    private BootcampReportMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new BootcampReportMapper();
    }

    @Test
    void documentToReport_mapsCorrectly() {
        // Arrange
        Instant now = Instant.now();
        BootcampReportDocument document = new BootcampReportDocument(
                "doc-id-123",
                1L,
                "Java Reactive",
                "Spring WebFlux y MongoDB",
                3,
                10,
                5L,
                now
        );

        // Act
        BootcampReport report = mapper.documentToReport(document);

        // Assert
        assertThat(report).isNotNull();
        assertThat(report.getId()).isEqualTo(document.getId());
        assertThat(report.getBootcampId()).isEqualTo(document.getBootcampId());
        assertThat(report.getName()).isEqualTo(document.getName());
        assertThat(report.getDescription()).isEqualTo(document.getDescription());
        assertThat(report.getCapabilityCount()).isEqualTo(document.getCapabilityCount());
        assertThat(report.getTechnologyCount()).isEqualTo(document.getTechnologyCount());
        assertThat(report.getEnrolledCount()).isEqualTo(document.getEnrolledCount());
        assertThat(report.getCreatedAt()).isEqualTo(document.getCreatedAt());
    }

    @Test
    void reportToDocument_mapsCorrectly() {
        // Arrange
        Instant now = Instant.now();
        BootcampReport report = new BootcampReport(
                "report-id-123",
                1L,
                "Java Reactive",
                "Spring WebFlux y MongoDB",
                3,
                10,
                5L,
                now
        );

        // Act
        BootcampReportDocument document = mapper.reportToDocument(report);

        // Assert
        assertThat(document).isNotNull();
        assertThat(document.getId()).isEqualTo(report.getId());
        assertThat(document.getBootcampId()).isEqualTo(report.getBootcampId());
        assertThat(document.getName()).isEqualTo(report.getName());
        assertThat(document.getDescription()).isEqualTo(report.getDescription());
        assertThat(document.getCapabilityCount()).isEqualTo(report.getCapabilityCount());
        assertThat(document.getTechnologyCount()).isEqualTo(report.getTechnologyCount());
        assertThat(document.getEnrolledCount()).isEqualTo(report.getEnrolledCount());
        assertThat(document.getCreatedAt()).isEqualTo(report.getCreatedAt());
    }

    @Test
    void requestDtoToReport_mapsCorrectly() {
        // Arrange
        BootcampReportRequestDTO dto = new BootcampReportRequestDTO(
                1L,
                "Java Reactive",
                "Spring WebFlux y MongoDB",
                3,
                10
        );

        // Act
        BootcampReport report = mapper.requestDtoToReport(dto);

        // Assert
        assertThat(report).isNotNull();
        assertThat(report.getId()).isNull();
        assertThat(report.getBootcampId()).isEqualTo(dto.bootcampId());
        assertThat(report.getName()).isEqualTo(dto.name());
        assertThat(report.getDescription()).isEqualTo(dto.description());
        assertThat(report.getCapabilityCount()).isEqualTo(dto.capabilityCount());
        assertThat(report.getTechnologyCount()).isEqualTo(dto.technologyCount());
        assertThat(report.getEnrolledCount()).isNull();
        assertThat(report.getCreatedAt()).isNull();
    }
}
