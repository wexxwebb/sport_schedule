package services._inter;


import db.entities._inter.Sex;

import java.io.Serializable;
import java.util.List;

public interface SexListService extends Serializable {

    List<Sex> getSexList();
}
