package net.juulnielsen.samples.rsocket.handlers;

import net.juulnielsen.samples.rsocket.model.Data;
import net.juulnielsen.samples.rsocket.model.DataRequest;
import net.juulnielsen.samples.rsocket.repository.DataRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class DataRouterFunction {

    private final DataRepository dataRepository;

    public DataRouterFunction(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @MessageMapping("currentData")
    public Mono<Data> currentData(DataRequest dataRequest) {
        return dataRepository.findById(dataRequest.getId());
    }

    @MessageMapping("insertData")
    public Mono<Void> insertData(Data data) {
        dataRepository.insert(data);
        return Mono.empty();
    }
}
