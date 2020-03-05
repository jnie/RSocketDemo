package net.juulnielsen.samples.rsocket.initializer;

import net.juulnielsen.samples.rsocket.model.Data;
import net.juulnielsen.samples.rsocket.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    DataRepository dataRepository;

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public void run(String... args) {
        initialDataSetup();
    }

    private void initialDataSetup() {
        dataRepository.deleteAll()
        .thenMany(Flux.fromIterable(data()))
        .flatMap(dataRepository::save)
                .map(item -> "Item inserted: " + item)
        .subscribe(System.out::println);

    }


    private List<Data> data() {
        return Arrays.asList(new Data("1234", "Samsung TV"),
                new Data(null, "Samsung Phone"),
                new Data(null, "Samsung Sofa"),
                new Data(null, "Samsung Connector"),
                new Data(null, "Samsung Screen"),
                new Data(null, "Samsung Shoes"));
    }

}
