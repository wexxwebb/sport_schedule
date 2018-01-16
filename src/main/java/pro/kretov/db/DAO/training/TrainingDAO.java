package pro.kretov.db.DAO.training;

import pro.kretov.common.PersistType;
import pro.kretov.common.Result;
import pro.kretov.db.POJO.Training;

import java.util.List;

public interface TrainingDAO {
    Result<List<Training>> getAll();
    Result<String> persist(Training training, PersistType persistType);
}
