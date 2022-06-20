package com.yan.domaincrawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain")
public class DomainCrawlerController {

    private DomainCrawlerService domainCrawlerService;

    @Autowired
    public void setDomainCrawlerService(DomainCrawlerService domainCrawlerService) {
        this.domainCrawlerService = domainCrawlerService;
    }

    @GetMapping("/lookup/{name}")
    public String lookup(@PathVariable("name") String name){
        domainCrawlerService.crawl(name);
        return "crawling worked";
    }
}
