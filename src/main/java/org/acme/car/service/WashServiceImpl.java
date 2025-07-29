package org.acme.car.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.extern.jbosslog.JBossLog;
import org.acme.car.entity.Car;

import java.time.ZonedDateTime;

@ApplicationScoped
@Named("WashService")
@JBossLog
public class WashServiceImpl implements ActionService {
    @Override
    public Car performAction(Car car) {
        log.infof("WashService Action: %s", car);
        return car.withWashed(ZonedDateTime.now());
    }
}
