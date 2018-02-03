package db.dao.hiber;

import common.InsertType;
import common.Result;
import db.dao.SexDAO;
import db.entities.Sex;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

import static common.Messages.SUCCESS;

@Repository
public class SexDAOImpl implements SexDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Result<List<Sex>> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from SEX");
        List<Sex> sexList = query.getResultList();
        return new Result<>(sexList, true, SUCCESS);
    }

    @Override
    public Result<Sex> insert(Sex sex, InsertType insertType) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        sex.setId((long)session.save(sex));
        session.getTransaction().commit();
        session.close();
        return new Result<>(sex, true, SUCCESS);
    }
}
