package services;


import db.entities.Sex;

import java.io.Serializable;
import java.util.List;

public interface SexListService extends Serializable {

    List<Sex> getSexList();
}
