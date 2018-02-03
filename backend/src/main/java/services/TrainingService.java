package services;

import common.Result;
import db.entities.Training;

import java.io.Serializable;

public interface TrainingService extends Serializable {

    Result<String> addTraining(int userId, String date);

    Result<String> delTraining(int id);

    Result<Training> getById(int id);
}
