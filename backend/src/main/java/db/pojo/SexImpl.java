package db.pojo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement
@XmlType(propOrder = {"id", "sex"})
public class SexImpl implements Sex, Serializable {

    private int id;

    private String sex;

    public SexImpl() {

    }

    public SexImpl(int id, String sex) {
        this.id = id;
        this.sex = sex;
    }

    public SexImpl(String sex) {
        this.sex = sex;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
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
