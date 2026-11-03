package com.pragma.onclass_report_service.infraestructure.adapter;

import com.mongodb.client.result.UpdateResult;
import com.pragma.onclass_report_service.domain.model.BootcampReport;
import com.pragma.onclass_report_service.infraestructure.adapters.persistenceadapters.BootcampReportPersistenceAdapter;
import com.pragma.onclass_report_service.infraestructure.adapters.persistenceadapters.entity.BootcampReportDocument;
import com.pragma.onclass_report_service.infraestructure.adapters.persistenceadapters.repository.SpringDataBootcampReportRepository;
import com.pragma.onclass_report_service.infraestructure.entrypoints.mapper.BootcampReportMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BootcampReportPersistenceAdapterTest {

    @Mock
    private SpringDataBootcampReportRepository repository;

    @Mock
    private ReactiveMongoTemplate mongoTemplate;

    @Mock
    private BootcampReportMapper mapper;

    @InjectMocks
    private BootcampReportPersistenceAdapter adapter;

    @Test
    void saveBootcampReport_mapsAndSavesSuccessfully() {
        // Arrange
        BootcampReport report = new BootcampReport(
                "id-1", 1L, "Java Reactive", "Desc", 3, 10, 0L, Instant.now()
        );
        BootcampReportDocument document = new BootcampReportDocument(
                "id-1", 1L, "Java Reactive", "Desc", 3, 10, 0L, report.getCreatedAt()
        );

        when(mapper.reportToDocument(report)).thenReturn(document);
        when(repository.save(document)).thenReturn(Mono.just(document));
        when(mapper.documentToReport(document)).thenReturn(report);

        // Act & Assert
        StepVerifier.create(adapter.saveBootcampReport(report))
                .expectNext(report)
                .verifyComplete();

        verify(mapper, times(1)).reportToDocument(report);
        verify(repository, times(1)).save(document);
        verify(mapper, times(1)).documentToReport(document);
    }

    @Test
    void incrementEnrolledCount_executesUpdateFirstSuccessfully() {
        // Arrange
        Long bootcampId = 1L;
        UpdateResult updateResult = mock(UpdateResult.class);

        when(mongoTemplate.updateFirst(any(Query.class), any(Update.class), eq(BootcampReportDocument.class)))
                .thenReturn(Mono.just(updateResult));

        // Act & Assert
        StepVerifier.create(adapter.incrementEnrolledCount(bootcampId))
                .verifyComplete();

        // Verificamos que se llamó al template de mongo con la query y el update correctos
        verify(mongoTemplate, times(1)).updateFirst(
                any(Query.class),
                any(Update.class),
                eq(BootcampReportDocument.class)
        );
    }
}
