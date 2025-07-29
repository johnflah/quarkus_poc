package org.acme.car.factory;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.car.service.ActionService;
import org.acme.car.service.NullActionServiceImpl;

import java.util.Optional;

@ApplicationScoped
public class ActionFactory {

    private final ActionServiceRegistry actionServiceRegistry;
    private static final ActionService NULL_ACTION = new NullActionServiceImpl();
    public ActionFactory(ActionServiceRegistry actionServiceRegistry) {
        this.actionServiceRegistry = actionServiceRegistry;
    }

    public ActionService create(String serviceName){
        return Optional.ofNullable(actionServiceRegistry.getService(serviceName.toUpperCase()))
                .orElse(NULL_ACTION);
    }

}
