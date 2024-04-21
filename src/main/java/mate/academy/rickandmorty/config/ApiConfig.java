package mate.academy.rickandmorty.config;

import java.net.http.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {
    @Bean
    public ObjectMapper buildObjectMapper() { return new ObjectMapper(); }

    @Bean
    public HttpClient buildHttpClient() {
        return HttpClient.newHttpClient();
    }
}
