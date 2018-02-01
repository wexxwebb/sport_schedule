package services;

import common.Result;
import db.pojo.Training;

public interface TrainingService {

    Result<String> addTraining(int userId, String date);

    Result<String> delTraining(int id);

    Result<Training> getById(int id);
}
