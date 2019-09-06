package com.rexen.rest.app.config.akka;

import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import scala.collection.JavaConverters;
import scala.collection.Seq;
import java.util.Arrays;
import java.util.List;

/**
 * @author: GavinHacker
 * */
@Component
public class SpringExtension implements Extension {

    private volatile ApplicationContext applicationContext;

    public void initialize(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Props props(String actorBeanName) {
        List<Object> l = Arrays.asList(applicationContext, actorBeanName);
        Seq<Object> seq = JavaConverters.asScalaIteratorConverter(l.iterator()).asScala().toSeq();
        return Props.create(SpringActorProducer.class, seq);
    }

    public Props props(String actorBeanName, Object... args) {
        List<Object> l = Arrays.asList(applicationContext, actorBeanName, args);
        Seq<Object> seq = JavaConverters.asScalaIteratorConverter(l.iterator()).asScala().toSeq();
        return Props.create(SpringActorProducer.class, seq);
    }
}
