package services._interfaces;

import db.entities.inter.Training;

import java.io.Serializable;

public interface TrainingService extends Serializable {

    String addTraining(int userId, String date);

    boolean delTraining(int id);

    Training getById(int id);
}
