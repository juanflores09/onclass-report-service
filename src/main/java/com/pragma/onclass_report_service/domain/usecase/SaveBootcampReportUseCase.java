package com.pragma.onclass_report_service.domain.usecase;

import com.pragma.onclass_report_service.domain.api.SaveBootcampReportServicePort;
import com.pragma.onclass_report_service.domain.model.BootcampReport;
import com.pragma.onclass_report_service.domain.spi.BootcampReportPersistencePort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RequiredArgsConstructor
public class SaveBootcampReportUseCase implements SaveBootcampReportServicePort {

    private final BootcampReportPersistencePort bootcampReportPersistencePort;

    @Override
    public Mono<Void> saveReport(BootcampReport report) {
        if (report.getEnrolledCount() == null) {
            report.setEnrolledCount(0L);
        }
        if (report.getCreatedAt() == null) {
            report.setCreatedAt(Instant.now());
        }
        return bootcampReportPersistencePort.saveBootcampReport(report).then();
    }

    @Override
    public Mono<Void> incrementEnrollment(Long bootcampId) {
        return bootcampReportPersistencePort.incrementEnrolledCount(bootcampId);
    }
}
