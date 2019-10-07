package at.htl.quickstart.model;


import javax.persistence.*;

@Entity(name = "Person")
@NamedQueries({
        @NamedQuery(name = "Person.findById",query = "select i from Person i where i.id= :Id"),
        @NamedQuery(name = "Person.findAll",query = "select i from Person i")
})
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String surname;
    private String lastname;

    public Person() {
    }

    public Person(String surname, String lastname) {
        this.surname = surname;
        this.lastname = lastname;
    }

    //region Getter n Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    //endregion
}
