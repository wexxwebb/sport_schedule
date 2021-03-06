package db.dao.state;

import common.InsertType;
import common.Result;
import db.pojo.State;

import java.util.List;

public interface StateDAO {
    Result<List<State>> getAll();
    Result<String> insert(State state, InsertType insertType);
}
