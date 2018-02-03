package db.entities.Impl;

import db.entities.Sex;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement
@XmlType(propOrder = {"id", "sex"})
public class SexImpl implements Sex, Serializable {

    private long id;

    private String sex;

    public SexImpl() {

    }

    public SexImpl(long id, String sex) {
        this.id = id;
        this.sex = sex;
    }

    public SexImpl(String sex) {
        this.sex = sex;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getSex() {
        return sex;
    }

    @Override
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Sex{" +
                "id=" + id +
                ", sex='" + sex + '\'' +
                '}';
    }
}
