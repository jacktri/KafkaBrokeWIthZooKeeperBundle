package com.savory.kafkaallinone.kafka;

import java.io.InputStream;
import java.util.Properties;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.core.io.ClassPathResource;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;

public class EmbeddedKafka {

    private static KafkaLocal kafka;

    public void start() {
        var kafkaProperties = new Properties();
        try {
            InputStream kafkaProps = new ClassPathResource("kafkalocal.properties").getInputStream();
            kafkaProperties.load(kafkaProps);
            kafka = new KafkaLocal(kafkaProperties);
            createTopic();
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private static void createTopic() {

        var zookeeperConnect = "localhost:2181";
        var sessionTimeoutMs = 10 * 1000;
        var connectionTimeoutMs = 8 * 1000;

        ZkClient zkClient = new ZkClient(
            zookeeperConnect,
            sessionTimeoutMs,
            connectionTimeoutMs,
            ZKStringSerializer$.MODULE$);

        ZkUtils zkUtils = ZkUtils.apply(zkClient, true);
        String topic = "ero";
        var partitions = 1;
        var replication = 1;

        Properties props = new Properties();
        AdminUtils.createTopic(zkUtils, topic, partitions, replication, props, new RackAwareMode.Disabled$());
        zkUtils.close();
        zkClient.close();
    }
}
