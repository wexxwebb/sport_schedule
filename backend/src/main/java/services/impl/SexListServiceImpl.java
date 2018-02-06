package services.impl;

import common.Logged;
import db.dao._inter.SexDAO;
import db.entities._inter.Sex;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services._inter.SexListService;

import java.util.List;

@Service
public class SexListServiceImpl implements SexListService {

    @Logged
    private Logger logger;

    private SexDAO sexDAO;

    @Autowired
    public void setSexDAO(SexDAO sexDAO) {
        this.sexDAO = sexDAO;
    }

    public List<Sex> getSexList() {
        return sexDAO.getAll();
    }
}
