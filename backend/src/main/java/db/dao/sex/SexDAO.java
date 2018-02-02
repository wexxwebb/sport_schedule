package db.dao.sex;

import common.InsertType;
import common.Result;
import db.pojo.Sex;

import java.io.Serializable;
import java.util.List;

public interface SexDAO extends Serializable {

    Result<List<Sex>> getAll();
    Result<String> insert(Sex sex, InsertType insertType);
    
}
