package db.dao;

import common.InsertType;
import common.Result;
import db.entities.Sex;

import java.io.Serializable;
import java.util.List;

public interface SexDAO extends Serializable {

    Result<List<Sex>> getAll();
    Result<Sex> insert(Sex sex, InsertType insertType);
    
}
