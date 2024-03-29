package at.htl.quickstart.rest;

import at.htl.quickstart.business.PersonFacade;
import at.htl.quickstart.model.Person;


import javax.inject.Inject;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

@Path("persons")
public class PersonResource {
    @Inject
    PersonFacade personFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersons(){
        List<Person> persons = personFacade.get();
        return Response.ok().entity(persons).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersons(@PathParam("id")long id){
        Person person = personFacade.get(id);
        return Response.ok().entity(person).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id){
        Person entity = personFacade.get(id);
        if(entity != null){
            personFacade.remove(entity);
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(Person entity){
        try {
            entity = personFacade.save(entity);
        }catch(PersistenceException e){
            return Response.status(400).build();
        }
        return Response.ok().entity(entity).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(Person entity){
        entity = personFacade.update(entity);
        return Response.ok().entity(entity).build();
    }
}
