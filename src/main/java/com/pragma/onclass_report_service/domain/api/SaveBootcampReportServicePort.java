package com.pragma.onclass_report_service.domain.api;

import com.pragma.onclass_report_service.domain.model.BootcampReport;
import reactor.core.publisher.Mono;

public interface SaveBootcampReportServicePort {

    Mono<Void> saveReport(BootcampReport report);
    Mono<Void> incrementEnrollment(Long bootcampId);

}
