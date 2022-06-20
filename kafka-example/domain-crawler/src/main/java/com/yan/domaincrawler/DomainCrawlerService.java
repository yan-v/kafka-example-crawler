package com.yan.domaincrawler;

import com.yan.common.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DomainCrawlerService {

    private KafkaTemplate<String, Domain> kafkaTemplate;
    private final String TOPIC = "web-domains";

    @Autowired
    public DomainCrawlerService(KafkaTemplate<String, Domain> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void crawl(String name){
        Mono<DomainList> domainsList = WebClient.create()
                .get()
                .uri("https://api.domainsdb.info/v1/domains/search?domain=" + name + "&limit=100")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(DomainList.class);

        domainsList.subscribe(domainList -> {
            domainList.domains.forEach(domain -> {
                kafkaTemplate.send(TOPIC, domain);
                System.out.println(" DOMAIN: " + domain.getDomain() + "[Dead: " + domain.isDead() + "]");
            });
        });
    }
}
