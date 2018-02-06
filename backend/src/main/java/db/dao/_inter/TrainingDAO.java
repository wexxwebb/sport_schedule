package db.dao._inter;

import common.TimePeriod;
import db.entities.Impl.TrainingImpl;
import db.entities._inter.Training;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

public interface TrainingDAO extends Serializable {

    @Nullable
    List<Training> getAll(long userId, TimePeriod timePeriod);

    @Nullable
    TrainingImpl insert(TrainingImpl training);

    boolean delete(long id);

    @Nullable
    Training getById(long id);
}
