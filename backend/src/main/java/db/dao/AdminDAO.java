package db.dao;

import common.InsertType;
import common.Result;
import db.pojo.AdminData;

import java.io.Serializable;
import java.util.List;

public interface AdminDAO extends Serializable {

    Result<List<AdminData>> getAll();

    Result<String> insert(AdminData adminData, InsertType insertType);
}
