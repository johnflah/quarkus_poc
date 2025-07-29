package org.acme.car.service;

import lombok.extern.jbosslog.JBossLog;
import org.acme.car.entity.Car;

@JBossLog
public class NullActionServiceImpl implements ActionService {
    Car nullCar = new Car();
    @Override
    public Car performAction(Car car) {
        System.out.println("Nothing happening here");
        log.infof("Null Service Action: %s", car);

        return nullCar;
    }
}
