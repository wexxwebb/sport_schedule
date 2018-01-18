package consoleTest.persist;

import common.Result;
import db.DAO.admin.AdminDAO;
import db.DAO.admin.AdminDAOImpl;
import db.POJO.AdminData;
import db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static common.PersistType.NEW;
import static common.PersistType.RESTORE;

public class TestAdmin {
    public static void main(String[] args) {

        AdminDAO adminDAO = new AdminDAOImpl(
                ConnectionManagerImpl.getInstance()
        );

        {
            AdminData adminData = new AdminData(1);
            Result<String> result;
            if ((result = adminDAO.persist(adminData, NEW)).isSuccess()) {
                System.out.println(result.getResult());
            } else {
                System.out.println(result.getMessage());
            }
        }

        {
            AdminData adminData = new AdminData(25, 25);
            Result<String> result;
            if ((result = adminDAO.persist(adminData, RESTORE)).isSuccess()) {
                System.out.println(result.getResult());
            } else {
                System.out.println(result.getMessage());
            }
        }

        Result<List<AdminData>> result = adminDAO.getAll();
        if (result != null && result.isSuccess()) {
            for (AdminData adminData : result.getResult()) {
                System.out.println(adminData.getId() + " " + adminData.getUserId());
            }
        } else

        {
            System.out.println(result.getMessage());
        }

    }
}
