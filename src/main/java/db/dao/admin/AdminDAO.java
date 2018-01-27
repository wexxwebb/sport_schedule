package db.dao.admin;

import common.InsertType;
import common.Result;
import db.pojo.AdminData;

import java.util.List;

public interface AdminDAO {
    Result<List<AdminData>> getAll();
    Result<String> insert(AdminData adminData, InsertType insertType);
}
