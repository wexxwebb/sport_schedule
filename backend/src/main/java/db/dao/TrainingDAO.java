package db.dao;

import common.InsertType;
import common.Result;
import common.TimePeriod;
import db.entities.Training;

import java.io.Serializable;
import java.util.List;

public interface TrainingDAO extends Serializable {

    Result<List<Training>> getAll(long userId, TimePeriod timePeriod);

    Result<Training> insert(Training training, InsertType insertType);

    Result<String> delete(int id);

    Result<Training> getByid(int id);
}
