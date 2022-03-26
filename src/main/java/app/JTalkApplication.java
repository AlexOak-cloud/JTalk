package app;

import app.utills.FileUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

@Configuration
@SpringBootApplication
@EnableJpaRepositories
public class JTalkApplication {


    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.of(50,DataUnit.MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(50,DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

    @Bean
    public FileUtil getFileUtil(){
        return new FileUtil();
    }

    public static void main(String[] args) {
        SpringApplication.run(JTalkApplication.class, args);
    }


}