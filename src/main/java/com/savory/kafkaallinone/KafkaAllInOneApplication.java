package com.savory.kafkaallinone;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.savory.kafkaallinone.kafka.EmbeddedKafka;
import com.savory.kafkaallinone.zookeeper.EmbeddedZookeeper;

public class KafkaAllInOneApplication {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newWorkStealingPool();
        var zookeeper = new EmbeddedZookeeper();
        var kafka = new EmbeddedKafka();
        executorService.execute(zookeeper::start);
        Thread.sleep(10000);
        kafka.start();
    }
}
