package db.pojo;

import java.io.Serializable;

public interface Sex extends Serializable {
    long getId();

    void setId(long id);

    String getSex();

    void setSex(String sex);

    @Override
    String toString();
}
