package com.yan.domainprocessor;

//import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import com.yan.common.Domain;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class DomainKafkaProcessor {

//    LogAndContinueExceptionHandler
    @Bean
    public Function<KStream<String, Domain>, KStream<String, Domain>> domainProcessor() {

        return kstream -> kstream.filter((key, domain) -> {
            if(domain.isDead()) {
                System.out.println(" Dead domain: " + domain.getDomain());
            } else {
                System.out.println(" Live domain: " + domain.getDomain());
            }
            return  !domain.isDead();
        });
    }
}
