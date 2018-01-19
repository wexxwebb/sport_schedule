package consoleTest.persist;

import common.Result;
import db.DAO.state.StateDAO;
import db.DAO.state.StateDAOImpl;
import db.POJO.State;
import db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static common.PersistType.NEW;
import static common.PersistType.RESTORE;

public class TestState {
    public static void main(String[] args) {
        StateDAO stateDAO = new StateDAOImpl(
                ConnectionManagerImpl.getInstance()
        );

        {
            State state = new State("normal");
            Result<String> result;
            if ((result = stateDAO.insert(state, NEW)).isSuccess()) {
                System.out.println(result.getResult());
            } else {
                System.out.println(result.getMessage());
            }
        }

        {
            State state = new State(25, "blocked");
            Result<String> result;
            if ((result = stateDAO.insert(state, RESTORE)).isSuccess()) {
                System.out.println(result.getResult());
            } else {
                System.out.println(result.getMessage());
            }
        }

        Result<List<State>> result = stateDAO.getAll();
        if (result != null && result.isSuccess()) {
            for (State state : result.getResult()) {
                System.out.println(state.getId() + " " + state.getState());
            }
        } else {
            System.out.println(result.getMessage());
        }

    }
}
