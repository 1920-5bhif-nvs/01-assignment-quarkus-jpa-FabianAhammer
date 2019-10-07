package at.htl.quickstart.rest;

import at.htl.quickstart.business.PlanFacade;
import at.htl.quickstart.model.Plan;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("plans")
public class PlanResource {
    @Inject
    PlanFacade planFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlans(){
        List<Plan> plans = planFacade.get();
        return Response.ok().entity(plans).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlans(@PathParam("id")long id){
        Plan plan = planFacade.get(id);
        return Response.ok().entity(plan).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id){
        Plan entity = planFacade.get(id);
        if(entity != null){
            planFacade.remove(entity);
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(Plan entity){
        try {
            entity = planFacade.save(entity);
        }catch(PersistenceException e){
            return Response.status(400).build();
        }
        return Response.ok().entity(entity).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(Plan entity){
        entity = planFacade.update(entity);
        return Response.ok().entity(entity).build();
    }
}
