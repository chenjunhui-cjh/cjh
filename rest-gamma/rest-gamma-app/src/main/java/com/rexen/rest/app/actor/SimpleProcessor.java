package com.rexen.rest.app.actor;

import akka.actor.AbstractActor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author: GavinHacker
 * @description:
 * @date: Created in 下午4:27 2019/3/18
 * @modifiedBy:
 */
@Component("simpleProcessor")
@Scope("prototype")
public class SimpleProcessor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchAny(x -> System.out.println("This is processor, hello there. Receive message:" + x)).build();
    }
}
