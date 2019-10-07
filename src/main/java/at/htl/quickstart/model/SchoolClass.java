package at.htl.quickstart.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;

@Entity(name = "schoolclass")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;

    @JsonbTransient
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pupil> pupils = new ArrayList<>();

    public SchoolClass() {
    }

    public SchoolClass(String className, List<Pupil> pupils) {
        this.className = className;
        this.pupils = pupils;
    }

    //region Getter n setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(List<Pupil> pupils) {
        this.pupils = pupils;
    }
    //endregion
}
