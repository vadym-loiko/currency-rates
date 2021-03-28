package org.vloiko.currencyrate;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CurrencyRatesApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(CurrencyRatesApplication.class, args);
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new RateServiceImpl())
                .build();

        server.start();
        server.awaitTermination();
    }

}
