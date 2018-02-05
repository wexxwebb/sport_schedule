package db.dao._interfaces;

import db.entities.Impl.SexImpl;
import db.entities.inter.Sex;

import java.io.Serializable;
import java.util.List;

public interface SexDAO extends Serializable {

    List<Sex> getAll();

    SexImpl insert(SexImpl sex);

}
