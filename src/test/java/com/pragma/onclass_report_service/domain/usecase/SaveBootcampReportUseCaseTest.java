package com.pragma.onclass_report_service.domain.usecase;

import com.pragma.onclass_report_service.domain.model.BootcampReport;
import com.pragma.onclass_report_service.domain.spi.BootcampReportPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveBootcampReportUseCaseTest {

    @Mock
    private BootcampReportPersistencePort persistencePort;

    @InjectMocks
    private SaveBootcampReportUseCase useCase;

    private BootcampReport report;

    @BeforeEach
    void setUp() {
        report = new BootcampReport();
        report.setBootcampId(1L);
        report.setName("Java Reactive");
        report.setDescription("Spring WebFlux y R2DBC");
        report.setCapabilityCount(3);
        report.setTechnologyCount(10);
    }

    @Test
    void saveReport_whenFieldsAreNull_setsDefaultsAndSaves() {
        // Arrange
        when(persistencePort.saveBootcampReport(any(BootcampReport.class)))
                .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        // Act & Assert
        StepVerifier.create(useCase.saveReport(report))
                .verifyComplete();

        verify(persistencePort, times(1)).saveBootcampReport(argThat(savedReport ->
                savedReport.getEnrolledCount() == 0L && savedReport.getCreatedAt() != null
        ));
    }

    @Test
    void saveReport_whenFieldsAreProvided_preservesThemAndSaves() {
        // Arrange
        Instant fixedTime = Instant.parse("2026-11-03T00:00:00Z");
        report.setEnrolledCount(5L);
        report.setCreatedAt(fixedTime);

        when(persistencePort.saveBootcampReport(any(BootcampReport.class)))
                .thenReturn(Mono.just(report));

        // Act & Assert
        StepVerifier.create(useCase.saveReport(report))
                .verifyComplete();

        verify(persistencePort, times(1)).saveBootcampReport(argThat(savedReport ->
                savedReport.getEnrolledCount() == 5L && savedReport.getCreatedAt().equals(fixedTime)
        ));
    }

    @Test
    void incrementEnrollment_callsPersistencePort() {
        // Arrange
        Long bootcampId = 1L;
        when(persistencePort.incrementEnrolledCount(bootcampId))
                .thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(useCase.incrementEnrollment(bootcampId))
                .verifyComplete();

        verify(persistencePort, times(1)).incrementEnrolledCount(bootcampId);
    }
}
