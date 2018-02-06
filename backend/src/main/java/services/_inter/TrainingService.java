package services._inter;

import db.entities._inter.Training;

import java.io.Serializable;

public interface TrainingService extends Serializable {

    String addTraining(long userId, String date);

    String delTraining(long id);

    Training getById(long id);
}
