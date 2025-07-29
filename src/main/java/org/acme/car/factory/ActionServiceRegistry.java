package org.acme.car.factory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.jbosslog.JBossLog;
import org.acme.car.service.ActionService;

import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
@JBossLog
public class ActionServiceRegistry {
    private final Map<String, ActionService> actionServiceMap;

    @Inject
    public ActionServiceRegistry(@Any Instance<ActionService> actionServices){
        log.trace(actionServices);
        actionServiceMap = actionServices
                .stream()
                .filter(bean -> {
                    Class<?> originalClass = bean.getClass().getSuperclass(); // avoid _ClientProxy
                    return originalClass.isAnnotationPresent(Named.class);
                })
                .collect(Collectors.toMap(
                        bean -> bean.getClass().getSuperclass().getAnnotation(Named.class).value().toUpperCase(),
                        bean -> bean
                ));
//        actionServiceMap = actionServices
//                .stream()
////                .filter(b -> b.getClass().isAnnotationPresent(Named.class))
//                .collect(Collectors.toMap(
//                        bean -> bean.getClass().getSimpleName().toUpperCase(),
//                        bean -> bean
//                ));
//        actionServiceMap = actionServices
//                .stream()
//                .filter(b -> b.getClass().isAnnotationPresent(Named.class))
//                .collect(Collectors.toMap(
//                        bean -> bean.getClass().getAnnotation(Named.class).value().toUpperCase(),
//                        bean -> bean
//                ));
    }

    public ActionService getService(String key){
        return actionServiceMap.get(key.toUpperCase());
    }
}
