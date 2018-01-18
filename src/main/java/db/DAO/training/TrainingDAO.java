package db.DAO.training;

import common.PersistType;
import common.Result;
import db.POJO.Training;

import java.util.List;

public interface TrainingDAO {
    Result<List<Training>> getAll();
    Result<String> persist(Training training, PersistType persistType);
}
