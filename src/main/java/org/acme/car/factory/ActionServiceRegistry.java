package org.acme.car.factory;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.BeanManager;
import org.acme.car.service.ActionService;

import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class ActionServiceRegistry {
    private final BeanManager beanManager;
    private Map<String, ActionService> actionServiceMap;

    public ActionServiceRegistry(BeanManager beanManager) {
        this.beanManager = beanManager;
    }

    @PostConstruct
    void init(){
        actionServiceMap = beanManager.getBeans(ActionService.class)
                .stream()
                .filter( b -> b.getName() != null)
                .collect(Collectors.toMap(bean -> bean.getName().toUpperCase(), bean -> (ActionService) beanManager.getReference(bean,ActionService.class, beanManager.createCreationalContext(bean))));
    }

    public ActionService getService(String key){
        return actionServiceMap.get(key);
    }
}
