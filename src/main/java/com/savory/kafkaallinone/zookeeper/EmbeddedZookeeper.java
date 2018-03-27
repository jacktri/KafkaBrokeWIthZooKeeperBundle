package com.savory.kafkaallinone.zookeeper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class EmbeddedZookeeper {

    private static final Logger logger = LoggerFactory.getLogger(EmbeddedZookeeper.class);

    public void start() {
        try {
            FileUtils.deleteDirectory(new File("/tmp"));
        }
        catch(IOException e){
            logger.error(e.getMessage());
        }
        initKafka();
    }

    private static void initKafka() {
        Properties zkProperties = new Properties();

        try {
            InputStream zooKeeperProps = new ClassPathResource("zklocal.properties").getInputStream();
            zkProperties.load(zooKeeperProps);

            ZooKeeperLocal zookeeper = new ZooKeeperLocal(zkProperties);
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
