package db.entities.Impl;

import db.entities.inter.Sex;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Collection;

@XmlRootElement
@XmlType(propOrder = {"id", "name"})

@Entity(name = "Sex")
@Table(name = "pex")
public class SexImpl implements Sex, Serializable {

    private long id;
    private String name;
    private Collection<PersonImpl> personCollection;

    public SexImpl() {
    }

    public SexImpl(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SexImpl(String name) {
        this.name = name;
    }

    @Override

    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "sex_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sex{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "sex")
    public Collection<PersonImpl> getPersonCollection() {
        return personCollection;
    }

    public void setPersonCollection(Collection<PersonImpl> personCollection) {
        this.personCollection = personCollection;
    }
}
