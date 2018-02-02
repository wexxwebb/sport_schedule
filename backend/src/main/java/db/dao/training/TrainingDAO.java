package db.dao.training;

import common.InsertType;
import common.Result;
import common.TimePeriod;
import db.pojo.Training;

import java.io.Serializable;
import java.util.List;

public interface TrainingDAO extends Serializable {

    Result<List<Training>> getAll(int userId, TimePeriod timePeriod);

    Result<Training> insert(Training training, InsertType insertType);

    Result<String> delete(int id);

    Result<Training> getByid(int id);
}