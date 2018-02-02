package db.pojo;

import java.io.Serializable;

public interface Sex extends Serializable {
    int getId();

    void setId(int id);

    String getSex();

    void setSex(String sex);

    @Override
    String toString();
}
