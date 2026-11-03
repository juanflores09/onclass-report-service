package com.pragma.onclass_report_service.infraestructure.entrypoints.handler;

import com.pragma.onclass_report_service.domain.api.SaveBootcampReportServicePort;
import com.pragma.onclass_report_service.infraestructure.entrypoints.dto.BootcampReportRequestDTO;
import com.pragma.onclass_report_service.infraestructure.entrypoints.mapper.BootcampReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BootcampReportHandler {

    private final SaveBootcampReportServicePort saveBootcampReportServicePort;
    private final BootcampReportMapper mapper;

    public Mono<ServerResponse> createReport(ServerRequest request) {
        return request.bodyToMono(BootcampReportRequestDTO.class)
                .map(mapper::requestDtoToReport)
                .flatMap(saveBootcampReportServicePort::saveReport)
                .then(ServerResponse.status(HttpStatus.CREATED).build());
    }

    public Mono<ServerResponse> incrementEnrollment(ServerRequest request) {
        Long bootcampId = Long.valueOf(request.pathVariable("bootcampId"));
        return saveBootcampReportServicePort.incrementEnrollment(bootcampId)
                .then(ServerResponse.ok().build());
    }
}
