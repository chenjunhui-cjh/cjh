package com.rexen.rest.app.config.akka;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :GavinHacker
 * */
@Configuration
public class AkkaConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringExtension springExtension;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create("actorSystem",akkaConfiguration());
        springExtension.initialize(applicationContext);
        return actorSystem;
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }
}
