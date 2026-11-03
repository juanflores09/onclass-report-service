package com.pragma.onclass_report_service.infraestructure.entrypoints;

import com.pragma.onclass_report_service.infraestructure.entrypoints.handler.BootcampReportHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ReportRouter {

    @Bean
    public RouterFunction<ServerResponse> reportRoutes(BootcampReportHandler handler) {
        return route()
                .POST("/api/v1/reports/bootcamps", handler::createReport)
                .PATCH("/api/v1/reports/bootcamps/{bootcampId}/increment-enrollment", handler::incrementEnrollment)
                .build();
    }
}
