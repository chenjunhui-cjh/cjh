package com.rexen.rest.app.actor;

import akka.actor.AbstractActor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author: GavinHacker
 * @description:
 * @date: Created in 下午4:27 2019/3/18
 * @modifiedBy:
 */
@Component("simpleProducer")
@Scope("prototype")
public class SimpleProducer extends AbstractActor {

    final private CompletableFuture<String> future;

    public SimpleProducer(CompletableFuture<String> future){
        this.future=future;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchAny(x -> {
            System.out.println("This is producer, hello there. Receive message:" + x);
            this.getSender().tell("[producer reply] have you message", getSelf());
        }).build();
    }
}
