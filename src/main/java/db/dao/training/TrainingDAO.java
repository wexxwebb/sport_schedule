package db.dao.training;

import common.InsertType;
import common.Result;
import db.pojo.Training;

import java.util.List;

public interface TrainingDAO {
    Result<List<Training>> getAll();
    Result<Training> insert(Training training, InsertType insertType);
    Result<String> delete(int id);
    public Result<Training> getByid(int id);
}
