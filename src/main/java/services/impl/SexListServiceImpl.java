package services.impl;

import db.DAO.sex.SexDAO;
import db.DAO.sex.SexDAOImpl;
import db.POJO.Sex;
import db.connectionManager.ConnectionManagerImpl;
import services.SexListService;

import java.util.List;

public class SexListServiceImpl implements SexListService {

    private SexDAO sexDAO = new SexDAOImpl(ConnectionManagerImpl.getInstance());

    public SexDAO getSexDAO() {
        return sexDAO;
    }

    public void setSexDAO(SexDAO sexDAO) {
        this.sexDAO = sexDAO;
    }

    public List<Sex> getSexList() {
        return sexDAO.getAll().get();
    }
}
