package db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface DAO {

    static <R, ARG> R doIt(BiFunction<Session, ARG, R> function, ARG arg,
                           SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        R result = function.apply(session, arg);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    static <R> R doIt(Function<Session, R> function,
                           SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        R result = function.apply(session);
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
