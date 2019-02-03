package com.house.service.schedual;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.LongAdder;

@Component
public class OutSchedualService {

    static final LongAdder count = new LongAdder();


    @Scheduled(cron = "0/5 * * * * ?")
    public void testOut() {
        count.increment();
        System.out.println("count : " + count.longValue());
    }

}
