package exercise.coding.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import com.lightstep.tracer.jre.JRETracer;
import io.opentracing.Tracer;

import java.net.MalformedURLException;

public class TracerConfiguration {

    private static final int VERBOSITY_MAX = 4;

    @Value("lightstep.api.token")
    private String accessToken;

    @Bean
    Tracer tracer() throws MalformedURLException{

        return new JRETracer(
                new com.lightstep.tracer.shared.Options.OptionsBuilder()
                .withAccessToken(accessToken)
                .withComponentName("business logic")
                .withVerbosity(VERBOSITY_MAX)
                .withCollectorProtocol("https")
                .withCollectorHost("colector-grpc.lightstep.com")
                .withCollectorPort(443)
                .build()
        );
    }
}
