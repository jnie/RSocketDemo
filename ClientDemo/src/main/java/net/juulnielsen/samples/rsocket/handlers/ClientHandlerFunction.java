package net.juulnielsen.samples.rsocket.handlers;

import net.juulnielsen.samples.rsocket.model.Data;
import net.juulnielsen.samples.rsocket.model.DataRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ClientHandlerFunction {

    private final RSocketRequester rSocketRequester;

    public ClientHandlerFunction(RSocketRequester.Builder rSocketRequesterBuilder) {
        this.rSocketRequester = rSocketRequesterBuilder
        .connectTcp("localhost", 7000).block();
    }

    public Mono<ServerResponse> data(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        rSocketRequester
                                .route("currentData")
                                .data(new DataRequest(id))
                                .retrieveMono(Data.class), Data.class
                );
    }

    public Mono<ServerResponse> insert(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Data.class)
                .flatMap(data -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .build(rSocketRequester
                                        .route("insertData")
                                        .data(data)
                                        .send()));
    }
}
