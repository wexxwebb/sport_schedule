package consoleTest.persist;

import common.Result;
import db.DAO.admin.AdminDAO;
import db.DAO.admin.AdminDAOImpl;
import db.POJO.AdminData;
import db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

public class TestAdmin {
    public static void main(String[] args) {

        AdminDAO adminDAO = new AdminDAOImpl(
                ConnectionManagerImpl.getInstance()
        );

        {
            AdminData adminData = new AdminData(1);
            Result<String> result;
            if ((result = adminDAO.insert(adminData, NEW)).isSuccess()) {
                System.out.println(result.get());
            } else {
                System.out.println(result.getMessage());
            }
        }

        {
            AdminData adminData = new AdminData(25, 25);
            Result<String> result;
            if ((result = adminDAO.insert(adminData, RESTORE)).isSuccess()) {
                System.out.println(result.get());
            } else {
                System.out.println(result.getMessage());
            }
        }

        Result<List<AdminData>> result = adminDAO.getAll();
        if (result != null && result.isSuccess()) {
            for (AdminData adminData : result.get()) {
                System.out.println(adminData.getId() + " " + adminData.getUserId());
            }
        } else

        {
            System.out.println(result.getMessage());
        }

    }
}
