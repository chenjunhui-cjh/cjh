package com.rexen.rest.app.config.akka;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * @author :GavinHacker
 * */
public class SpringActorProducer implements IndirectActorProducer {

    final private ApplicationContext applicationContext;
    final private String actorBeanName;
    final private Object[] args;

    public SpringActorProducer(ApplicationContext applicationContext, String actorBeanName, Object... args) {
        this.applicationContext = applicationContext;
        this.actorBeanName = actorBeanName;
        this.args=args;
    }

    public SpringActorProducer(ApplicationContext applicationContext, String actorBeanName) {
        this.applicationContext = applicationContext;
        this.actorBeanName = actorBeanName;
        this.args = null;
    }

    @Override
    public Actor produce() {
        if(args == null){
            return (Actor) applicationContext.getBean(actorBeanName);
        } else {
            return (Actor) applicationContext.getBean(actorBeanName,args);
        }
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
    }
}
