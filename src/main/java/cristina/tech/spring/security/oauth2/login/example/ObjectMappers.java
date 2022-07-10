package cristina.tech.spring.security.oauth2.login.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static com.fasterxml.jackson.databind.DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class ObjectMappers {
    public static final ObjectMapper DEFAULT = Jackson2ObjectMapperBuilder.json()
            .modulesToInstall(ParameterNamesModule.class, JavaTimeModule.class)
            .featuresToDisable(WRITE_DATES_AS_TIMESTAMPS)
            .featuresToDisable(READ_DATE_TIMESTAMPS_AS_NANOSECONDS)
            .build();

}
