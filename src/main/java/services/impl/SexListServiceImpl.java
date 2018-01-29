package services.impl;

import db.dao.sex.SexDAO;
import db.pojo.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.SexListService;

import java.util.List;

@Service
public class SexListServiceImpl implements SexListService {

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
