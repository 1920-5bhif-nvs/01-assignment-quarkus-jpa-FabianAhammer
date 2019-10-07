package at.htl.quickstart.business;

import at.htl.quickstart.model.*;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

    @ApplicationScoped
    public class InitBean {

        @Inject
        EntityManager em;

        @Transactional
        void init(@Observes StartupEvent event){

            List<Pupil> pupils = new ArrayList<>();
            pupils.add(new Pupil("Max","Hofer","if120125",true));
            pupils.add(new Pupil("Erik","Mayerhofer","if130126",false));
            pupils.add(new Pupil("Stephan","Do","if125015",false));

            for (Pupil pupil : pupils) {
                em.persist(pupil);
            }
            SchoolClass schoolClass = new SchoolClass("4BHIF",pupils);
            em.persist(schoolClass);

            Teacher teacher = (new Teacher("Thomas","Stuetz","E91",schoolClass));
            em.persist(teacher);
            Subject sub = new Subject("Maths");
            em.persist(sub);
            Vintage vin = new Vintage(2017);
            em.persist(vin);
            Plan plan = (new Plan(LocalDate.now(),teacher,sub,vin));
            em.persist(plan);

        }
    }
