package pro.kretov.db.DAO.user;

import pro.kretov.common.PersistType;
import pro.kretov.common.Result;
import pro.kretov.db.POJO.UserData;

import java.util.List;

public interface UserDataDAO {
    Result<List<UserData>> getAll();
    Result<String> persist(UserData user, PersistType persistType);
}
