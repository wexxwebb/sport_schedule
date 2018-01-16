package pro.kretov.db.DAO.admin;

import pro.kretov.common.PersistType;
import pro.kretov.common.Result;
import pro.kretov.db.POJO.AdminData;

import java.util.List;

public interface AdminDAO {
    Result<List<AdminData>> getAll();
    Result<String> persist(AdminData adminData, PersistType persistType);
}
