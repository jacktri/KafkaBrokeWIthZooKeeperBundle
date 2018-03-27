package com.savory.kafkaallinone.kafka;

import java.io.IOException;
import java.util.Properties;
import kafka.server.KafkaConfig;
import kafka.server.KafkaServerStartable;


public class KafkaLocal {

    public KafkaServerStartable kafka;

    public KafkaLocal(Properties kafkaProperties) {
        KafkaConfig kafkaConfig = new KafkaConfig(kafkaProperties);
        kafka = new KafkaServerStartable(kafkaConfig);
        System.out.println("starting local kafka broker...");
        kafka.startup();
        System.out.println("done");
    }


    public void stop(){
        //stop kafka broker
        System.out.println("stopping kafka...");
        kafka.shutdown();
        System.out.println("done");
    }

}