package db.dao._interfaces;

import common.TimePeriod;
import db.entities.inter.Training;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

public interface TrainingDAO extends Serializable {

    @Nullable
    List<Training> getAll(long userId, TimePeriod timePeriod);

    Training insert(Training training);

    boolean delete(int id);

    Training getById(int id);
}
