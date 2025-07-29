package org.acme.car.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.extern.jbosslog.JBossLog;
import org.acme.car.entity.Car;

import java.time.ZonedDateTime;

@ApplicationScoped
@Named("EngineService")
@JBossLog
public class EngineServiceImpl implements ActionService {
    @Override
    public Car performAction(Car car) {
        log.infof("EngineService Action: %s", car);
        return car.withServiced(ZonedDateTime.now());
    }
}
