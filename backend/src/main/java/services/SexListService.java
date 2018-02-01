package services;


import db.pojo.Sex;

import java.io.Serializable;
import java.util.List;

public interface SexListService extends Serializable {

    List<Sex> getSexList();
}
