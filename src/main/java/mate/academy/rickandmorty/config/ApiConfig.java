package mate.academy.rickandmorty.config;

import java.net.http.HttpClient;
import java.util.Random;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {
    @Bean
    Random buildRandom() {
        return new Random();
    }

    @Bean
    ObjectMapper buildObjectMapper() {

        return new ObjectMapper();
    }

    @Bean
    HttpClient buildHttpClient() {
        return HttpClient.newHttpClient();
    }
}
