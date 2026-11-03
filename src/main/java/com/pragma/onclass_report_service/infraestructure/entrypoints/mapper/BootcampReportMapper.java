package com.pragma.onclass_report_service.infraestructure.entrypoints.mapper;

import com.pragma.onclass_report_service.domain.model.BootcampReport;
import com.pragma.onclass_report_service.infraestructure.adapters.persistenceadapters.entity.BootcampReportDocument;
import com.pragma.onclass_report_service.infraestructure.entrypoints.dto.BootcampReportRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class BootcampReportMapper {

    public BootcampReport documentToReport(BootcampReportDocument document) {
        return new BootcampReport(
                document.getId(),
                document.getBootcampId(),
                document.getName(),
                document.getDescription(),
                document.getCapabilityCount(),
                document.getTechnologyCount(),
                document.getEnrolledCount(),
                document.getCreatedAt()
        );
    }

    public BootcampReportDocument reportToDocument(BootcampReport report) {
        return new BootcampReportDocument(
                report.getId(),
                report.getBootcampId(),
                report.getName(),
                report.getDescription(),
                report.getCapabilityCount(),
                report.getTechnologyCount(),
                report.getEnrolledCount(),
                report.getCreatedAt()
        );
    }

    public BootcampReport requestDtoToReport(BootcampReportRequestDTO dto) {
        BootcampReport report = new BootcampReport();
        report.setBootcampId(dto.bootcampId());
        report.setName(dto.name());
        report.setDescription(dto.description());
        report.setCapabilityCount(dto.capabilityCount());
        report.setTechnologyCount(dto.technologyCount());
        return report;
    }
}
