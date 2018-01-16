package pro.kretov.main;

import pro.kretov.common.Result;
import pro.kretov.db.DAO.sex.SexDAO;
import pro.kretov.db.DAO.sex.SexDAOImpl;
import pro.kretov.db.POJO.Sex;
import pro.kretov.db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static pro.kretov.common.PersistType.NEW;
import static pro.kretov.common.PersistType.RESTORE;

public class TestSex {

    public static void main(String[] args) {
        SexDAO sexDAO = new SexDAOImpl(
                ConnectionManagerImpl.getInstance()
        );

        {
            Sex sex = new Sex("men");
            Result<String> result;
            if ((result = sexDAO.persist(sex, NEW)).isSuccess()) {
                System.out.println(result.getResult());
            } else {
                System.out.println(result.getMessage());
            }
        }

        {
            Sex sex = new Sex(25, "woman");
            Result<String> result;
            if ((result = sexDAO.persist(sex, RESTORE)).isSuccess()) {
                System.out.println(result.getResult());
            } else {
                System.out.println(result.getMessage());
            }
        }

        Result<List<Sex>> result = sexDAO.getAll();
        if (result != null && result.isSuccess()) {
            for (Sex sex : result.getResult()) {
                System.out.println(sex.getId() + " " + sex.getSex());
            }
        } else {
            System.out.println(result.getMessage());
        }

    }
}
