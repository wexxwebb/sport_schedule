package consoleTest.persist;

import common.Result;
import db.DAO.sex.SexDAO;
import db.DAO.sex.SexDAOImpl;
import db.POJO.Sex;
import db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

public class TestSex {

    public static void main(String[] args) {
        SexDAO sexDAO = new SexDAOImpl(
                ConnectionManagerImpl.getInstance()
        );

        {
            Sex sex = new Sex("men");
            Result<String> result;
            if ((result = sexDAO.insert(sex, NEW)).isSuccess()) {
                System.out.println(result.get());
            } else {
                System.out.println(result.getMessage());
            }
        }

        {
            Sex sex = new Sex(25, "woman");
            Result<String> result;
            if ((result = sexDAO.insert(sex, RESTORE)).isSuccess()) {
                System.out.println(result.get());
            } else {
                System.out.println(result.getMessage());
            }
        }

        Result<List<Sex>> result = sexDAO.getAll();
        if (result != null && result.isSuccess()) {
            for (Sex sex : result.get()) {
                System.out.println(sex.getId() + " " + sex.getSex());
            }
        } else {
            System.out.println(result.getMessage());
        }

    }
}
