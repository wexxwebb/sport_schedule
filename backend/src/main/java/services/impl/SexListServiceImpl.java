package services.impl;

import common.Logged;
import db.dao.sex.SexDAO;
import db.pojo.Sex;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.SexListService;

import java.io.Serializable;
import java.util.List;

@Service
public class SexListServiceImpl implements SexListService {

    @Logged
    private Logger logger;

    @Autowired
    private SexDAO sexDAO;

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
