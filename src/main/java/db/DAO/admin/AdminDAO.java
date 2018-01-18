package db.DAO.admin;

import common.PersistType;
import common.Result;
import db.POJO.AdminData;

import java.util.List;

public interface AdminDAO {
    Result<List<AdminData>> getAll();
    Result<String> persist(AdminData adminData, PersistType persistType);
}
