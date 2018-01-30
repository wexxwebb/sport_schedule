package service.impl;

import db.dao.sex.SexDAO;
import db.pojo.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SexListService;

import java.util.List;

@Service
public class SexListServiceImpl implements SexListService {

    private SexDAO sexDAO;

    public SexDAO getSexDAO() {
        return sexDAO;
    }

    @Autowired
    public void setSexDAO(SexDAO sexDAO) {
        this.sexDAO = sexDAO;
    }

    public List<Sex> getSexList() {
        return sexDAO.getAll().get();
    }
}
