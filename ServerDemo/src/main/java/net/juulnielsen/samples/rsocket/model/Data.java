package net.juulnielsen.samples.rsocket.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document  // same as relation DB @Entity
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    @Id
    private String id;
    private String message;
}
