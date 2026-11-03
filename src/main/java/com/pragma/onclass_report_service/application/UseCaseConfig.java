package com.pragma.onclass_report_service.application;

import com.pragma.onclass_report_service.domain.api.SaveBootcampReportServicePort;
import com.pragma.onclass_report_service.domain.spi.BootcampReportPersistencePort;
import com.pragma.onclass_report_service.domain.usecase.SaveBootcampReportUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public SaveBootcampReportServicePort saveBootcampReportServicePort(
            BootcampReportPersistencePort bootcampReportPersistencePort){
        return new SaveBootcampReportUseCase(bootcampReportPersistencePort);
    }
}
