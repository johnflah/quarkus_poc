package org.acme.car.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.car.entity.Car;
import org.acme.car.factory.ActionFactory;
import org.acme.car.service.CarService;

@Path("/car")
public class CarResource {
    private final CarService carService;
    private final ActionFactory actionFactory;

    public CarResource(CarService carService, ActionFactory actionFactory) {
        this.carService = carService;
        this.actionFactory = actionFactory;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index(){
        return Response.ok(carService.index()).build();
    }


    @POST()
    @Path("/{action}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response performAction(@PathParam("action") String action, Car car){
        return Response.ok(actionFactory.create(action).performAction(car)).build();
    }
}
