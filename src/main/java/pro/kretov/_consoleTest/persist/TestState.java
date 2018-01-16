package pro.kretov._consoleTest.persist;

import pro.kretov.common.Result;
import pro.kretov.db.DAO.state.StateDAO;
import pro.kretov.db.DAO.state.StateDAOImpl;
import pro.kretov.db.POJO.State;
import pro.kretov.db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static pro.kretov.common.PersistType.NEW;
import static pro.kretov.common.PersistType.RESTORE;

public class TestState {
    public static void main(String[] args) {
        StateDAO stateDAO = new StateDAOImpl(
                ConnectionManagerImpl.getInstance()
        );

        {
            State state = new State("normal");
            Result<String> result;
            if ((result = stateDAO.persist(state, NEW)).isSuccess()) {
                System.out.println(result.getResult());
            } else {
                System.out.println(result.getMessage());
            }
        }

        {
            State state = new State(25, "blocked");
            Result<String> result;
            if ((result = stateDAO.persist(state, RESTORE)).isSuccess()) {
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
