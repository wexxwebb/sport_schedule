package db.DAO.training;

import common.InsertType;
import common.Result;
import db.POJO.Training;

import java.util.List;

public interface TrainingDAO {
    Result<List<Training>> getAll();
    Result<String> insert(Training training, InsertType insertType);
}
