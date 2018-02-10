package services.impl;

import common.Logged;
import db.dao._inter.SexDAO;
import db.dao.excep.DataIsNotAvailableException;
import db.entities._inter.Sex;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services._inter.SexListService;
import services.excep.ServiceIsNotAvailableException;

import java.util.List;

@Service
public class SexListServiceImpl implements SexListService {

    @Logged
    private Logger logger;

    private SexDAO sexDAO;

    @Autowired
    public SexListServiceImpl(SexDAO sexDAO) {
        this.sexDAO = sexDAO;
    }

    public List<Sex> getSexList() throws ServiceIsNotAvailableException {
        try {
            return sexDAO.getAll();
        } catch (DataIsNotAvailableException e) {
            logger.error(e);
            throw new ServiceIsNotAvailableException(e);
        }
    }
}
