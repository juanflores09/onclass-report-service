package com.pragma.onclass_report_service.domain.spi;

import com.pragma.onclass_report_service.domain.model.BootcampReport;
import reactor.core.publisher.Mono;

public interface BootcampReportPersistencePort {

    Mono<BootcampReport> saveBootcampReport(BootcampReport report);
    Mono<Void> incrementEnrolledCount(Long bootcampId);

}
