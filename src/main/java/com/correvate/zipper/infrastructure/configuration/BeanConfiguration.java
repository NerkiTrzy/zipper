package com.correvate.zipper.infrastructure.configuration;

import com.correvate.zipper.ZipperApplication;
import com.correvate.zipper.domain.filesystem.ZipperFileSystem;
import com.correvate.zipper.domain.service.DomainZipperService;
import com.correvate.zipper.domain.service.ZipperService;
import com.correvate.zipper.infrastructure.filesystem.BasicZipperFileSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ZipperApplication.class)
public class BeanConfiguration {
    @Bean
    BasicZipperFileSystem basicZipperFileSystem() {
        return new BasicZipperFileSystem();
    }

    @Bean
    ZipperService zipperService(ZipperFileSystem zipperFileSystem) {
        return new DomainZipperService(zipperFileSystem);
    }
}