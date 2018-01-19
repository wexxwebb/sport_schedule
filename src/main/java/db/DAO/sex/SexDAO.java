package db.DAO.sex;

import common.PersistType;
import common.Result;
import db.POJO.Sex;

import java.util.List;

public interface SexDAO {

    Result<List<Sex>> getAll();
    Result<String> insert(Sex sex, PersistType persistType);
    
}
