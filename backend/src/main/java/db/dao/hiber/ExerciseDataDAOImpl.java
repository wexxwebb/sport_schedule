package db.dao.hiber;

import common.InsertType;
import common.Result;
import db.dao._interfaces.ExerciseDataDAO;
import db.entities.inter.ExerciseData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExerciseDataDAOImpl implements ExerciseDataDAO {
    @Override
    public Result<List<ExerciseData>> getAll() {
        return null;
    }

    @Override
    public Result<ExerciseData> insert(ExerciseData exerciseData, InsertType insertType) {
        return null;
    }

    @Override
    public Result<List<ExerciseData>> searchByName(String term) {
        return null;
    }

    @Override
    public Result<ExerciseData> getById(long id) {
        return null;
    }

    @Override
    public Result<String> delete(int id) {
        return null;
    }

    @Override
    public Result<ExerciseData> update(ExerciseData exerciseData) {
        return null;
    }
}
