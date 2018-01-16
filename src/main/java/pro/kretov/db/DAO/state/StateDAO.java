package pro.kretov.db.DAO.state;

import pro.kretov.common.PersistType;
import pro.kretov.common.Result;
import pro.kretov.db.POJO.State;

import java.util.List;

public interface StateDAO {
    Result<List<State>> getAll();
    Result<String> persist(State state, PersistType persistType);
}
