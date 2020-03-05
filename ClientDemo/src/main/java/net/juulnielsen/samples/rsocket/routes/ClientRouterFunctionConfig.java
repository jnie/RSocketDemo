package net.juulnielsen.samples.rsocket.routes;

import net.juulnielsen.samples.rsocket.handlers.ClientHandlerFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ClientRouterFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> routeFlux(ClientHandlerFunction handlerFunction) {
        return RouterFunctions
                .route(GET("current/{id}")
                        .and(accept(MediaType.APPLICATION_JSON)), handlerFunction::data)
                .andRoute(POST("insert")
                        .and(accept(MediaType.APPLICATION_JSON)), handlerFunction::insert);
    }

}
