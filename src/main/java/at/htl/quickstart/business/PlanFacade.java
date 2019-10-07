package at.htl.quickstart.business;

import at.htl.quickstart.model.Plan;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class PlanFacade {
    @PersistenceContext
    EntityManager em;

    public List<Plan> get(){
        TypedQuery<Plan> entities = em.createNamedQuery("Plan.findAll", Plan.class);
        return entities.getResultList();
    }

    public Plan get(long id){
        TypedQuery<Plan> entities = em.createNamedQuery("Plan.findById", Plan.class);
        entities.setParameter("Id",id);
        return entities.getSingleResult();
    }

    public void remove(Plan entity) {
        entity= em.merge(entity);
        em.remove(entity);
    }

    public Plan save(Plan entity) {
        entity=em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    public Plan update(Plan entity) {
        entity = em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }
}
