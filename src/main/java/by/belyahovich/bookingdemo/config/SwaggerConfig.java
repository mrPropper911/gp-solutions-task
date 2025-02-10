package by.belyahovich.bookingdemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Booking Demo Api Doc")
                        .version("1.0.0")
                        .contact(
                                new Contact()
                                        .name("Belyahovich Vadim")
                                        .url("https://t.me/mrc1in")
                        ));
    }
}
