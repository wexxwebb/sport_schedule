package db.DAO.state;

import common.PersistType;
import common.Result;
import db.POJO.State;

import java.util.List;

public interface StateDAO {
    Result<List<State>> getAll();
    Result<String> persist(State state, PersistType persistType);
}
