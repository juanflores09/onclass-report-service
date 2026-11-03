package com.pragma.onclass_report_service.infraestructure.adapters.persistenceadapters;

import com.pragma.onclass_report_service.domain.model.BootcampReport;
import com.pragma.onclass_report_service.domain.spi.BootcampReportPersistencePort;
import com.pragma.onclass_report_service.infraestructure.adapters.persistenceadapters.entity.BootcampReportDocument;
import com.pragma.onclass_report_service.infraestructure.adapters.persistenceadapters.repository.SpringDataBootcampReportRepository;
import com.pragma.onclass_report_service.infraestructure.entrypoints.mapper.BootcampReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class BootcampReportPersistenceAdapter implements BootcampReportPersistencePort {

    private final SpringDataBootcampReportRepository repository;
    private final ReactiveMongoTemplate mongoTemplate;
    private final BootcampReportMapper mapper;

    @Override
    public Mono<BootcampReport> saveBootcampReport(BootcampReport report) {
        BootcampReportDocument document = mapper.reportToDocument(report);
        return repository.save(document)
                .map(mapper::documentToReport);
    }

    @Override
    public Mono<Void> incrementEnrolledCount(Long bootcampId) {
        Query query = new Query(Criteria.where("bootcampId").is(bootcampId));
        Update update = new Update().inc("enrolledCount", 1);

        return mongoTemplate.updateFirst(query, update, BootcampReportDocument.class).then();
    }
}
