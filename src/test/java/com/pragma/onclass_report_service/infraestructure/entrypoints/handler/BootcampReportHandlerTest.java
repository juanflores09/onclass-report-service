package com.pragma.onclass_report_service.infraestructure.entrypoints.handler;

import com.pragma.onclass_report_service.domain.api.SaveBootcampReportServicePort;
import com.pragma.onclass_report_service.domain.model.BootcampReport;
import com.pragma.onclass_report_service.infraestructure.entrypoints.dto.BootcampReportRequestDTO;
import com.pragma.onclass_report_service.infraestructure.entrypoints.mapper.BootcampReportMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BootcampReportHandlerTest {

    @Mock
    private SaveBootcampReportServicePort saveBootcampReportServicePort;

    @Mock
    private BootcampReportMapper mapper;

    @InjectMocks
    private BootcampReportHandler handler;

    private BootcampReportRequestDTO requestDTO;
    private BootcampReport report;

    @BeforeEach
    void setUp() {
        requestDTO = new BootcampReportRequestDTO(
                1L,
                "Java Reactive",
                "Spring WebFlux y R2DBC",
                3,
                10
        );
        report = new BootcampReport();
        report.setBootcampId(1L);
    }

    @Test
    void createReport_whenValidRequest_returnsCreatedStatus() {
        // Arrange
        MockServerRequest serverRequest = MockServerRequest.builder()
                .body(Mono.just(requestDTO));

        when(mapper.requestDtoToReport(any(BootcampReportRequestDTO.class))).thenReturn(report);
        when(saveBootcampReportServicePort.saveReport(any(BootcampReport.class))).thenReturn(Mono.empty());

        // Act
        Mono<ServerResponse> responseMono = handler.createReport(serverRequest);

        // Assert
        StepVerifier.create(responseMono)
                .assertNext(response -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED))
                .verifyComplete();

        verify(mapper, times(1)).requestDtoToReport(any(BootcampReportRequestDTO.class));
        verify(saveBootcampReportServicePort, times(1)).saveReport(any(BootcampReport.class));
    }

    @Test
    void incrementEnrollment_whenValidId_returnsOkStatus() {
        // Arrange
        MockServerRequest serverRequest = MockServerRequest.builder()
                .pathVariable("bootcampId", "1")
                .build();

        when(saveBootcampReportServicePort.incrementEnrollment(1L)).thenReturn(Mono.empty());

        // Act
        Mono<ServerResponse> responseMono = handler.incrementEnrollment(serverRequest);

        // Assert
        StepVerifier.create(responseMono)
                .assertNext(response -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK))
                .verifyComplete();

        verify(saveBootcampReportServicePort, times(1)).incrementEnrollment(1L);
    }
}
