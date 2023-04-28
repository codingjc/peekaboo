package cn.codingjc.peekaboo.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private String title = "SpringDoc API";
    private String description = "SpringDoc Application";
    private String version = "v0.0.1";

    @Bean
    public OpenAPI springOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .description(description)
                        .version(version));
    }
}
