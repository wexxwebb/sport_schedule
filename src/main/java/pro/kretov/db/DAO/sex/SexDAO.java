package pro.kretov.db.DAO.sex;

import pro.kretov.common.PersistType;
import pro.kretov.common.Result;
import pro.kretov.db.POJO.Sex;

import java.util.List;

public interface SexDAO {

    Result<List<Sex>> getAll();
    Result<String> persist(Sex sex, PersistType persistType);
    
}
