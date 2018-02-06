package db.dao._inter;

import db.entities.Impl.SexImpl;
import db.entities._inter.Sex;

import java.io.Serializable;
import java.util.List;

public interface SexDAO extends Serializable {

    List<Sex> getAll();

    SexImpl insert(SexImpl sex);

}
