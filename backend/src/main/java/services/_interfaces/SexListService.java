package services._interfaces;


import db.entities.inter.Sex;

import java.io.Serializable;
import java.util.List;

public interface SexListService extends Serializable {

    List<Sex> getSexList();
}
