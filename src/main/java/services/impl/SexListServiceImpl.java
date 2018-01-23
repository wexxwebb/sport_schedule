package services.impl;

import db.dao.sex.SexDAO;
import db.dao.sex.SexDAOImpl;
import db.pojo.Sex;
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
