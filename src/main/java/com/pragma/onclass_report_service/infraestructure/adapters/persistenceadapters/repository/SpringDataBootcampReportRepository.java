package com.pragma.onclass_report_service.infraestructure.adapters.persistenceadapters.repository;

import com.pragma.onclass_report_service.infraestructure.adapters.persistenceadapters.entity.BootcampReportDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface SpringDataBootcampReportRepository extends ReactiveMongoRepository<BootcampReportDocument, String> {

    Mono<BootcampReportDocument> findByBootcampId(Long bootcampId);

}
