package org.acme.car.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.jbosslog.JBossLog;
import org.acme.car.entity.Car;
import org.acme.car.factory.ActionFactory;
import org.acme.car.service.CarService;
import org.acme.filter.RequestContextObject;

@Path("/car")
@JBossLog
public class CarResource {
    private final CarService carService;
    private final ActionFactory actionFactory;

    @Context
    ContainerRequestContext requestContext;

    @Inject
    RequestContextObject requestContextObject;

    public CarResource(CarService carService, ActionFactory actionFactory) {
        this.carService = carService;
        this.actionFactory = actionFactory;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index(){
        String greeting = (String)requestContext.getProperty("greeting");

        log.info(greeting);
        log.infof("Request Context: %s", requestContextObject);
        return Response.ok(carService.index()).build();
    }


    @POST()
    @Path("/{action}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response performAction(@PathParam("action") String action, Car car){
        return Response.ok(actionFactory.create(action).performAction(car)).build();
    }
}
