package net.juulnielsen.samples.rsocket.repository;

import net.juulnielsen.samples.rsocket.model.Data;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DataRepository extends ReactiveMongoRepository<Data, String> {

}
