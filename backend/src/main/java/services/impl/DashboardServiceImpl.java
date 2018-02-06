package services.impl;

import common.Logged;
import db.dao._inter.UserDataDAO;
import db.entities._inter.Training;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services._inter.DashboardService;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Calendar.*;

@Component
public class DashboardServiceImpl implements DashboardService {

    @Logged
    private Logger logger;

    private UserDataDAO userDataDAO;

    public DashboardServiceImpl() {
    }

    @Autowired
    public void setUserDataDAO(UserDataDAO userDataDAO) {
        this.userDataDAO = userDataDAO;
    }

    @Override
    public List<Training> getTodayTrainingList(long userId) {
        final Calendar startToday = Calendar.getInstance();
        startToday.set(HOUR_OF_DAY, 0);
        startToday.set(MINUTE, 0);
        startToday.set(SECOND, 0);
        startToday.set(MILLISECOND, 0);
        final Calendar endToday = Calendar.getInstance();
        startToday.set(HOUR_OF_DAY, 0);
        endToday.set(MINUTE, 59);
        endToday.set(SECOND, 59);
        endToday.set(MILLISECOND, 999);

        return userDataDAO.getById(userId)
                .getTrainingCollection()
                .stream()
                .filter(training ->
                        training.getTrainingDate().getTime() >= startToday.getTimeInMillis() &&
                                training.getTrainingDate().getTime() <= endToday.getTimeInMillis())
                .collect(Collectors.toList());
    }

    @Override
    public List<Training> getFutureTrainingList(long userId) {
        final Calendar endToday = Calendar.getInstance();
        endToday.set(HOUR_OF_DAY, 23);
        endToday.set(MINUTE, 59);
        endToday.set(SECOND, 59);
        endToday.set(MILLISECOND, 0);
        return userDataDAO.getById(userId)
                .getTrainingCollection()
                .stream()
                .filter(training ->
                        training.getTrainingDate().getTime() > endToday.getTimeInMillis())
                .collect(Collectors.toList());
    }

    @Override
    public List<Training> getPastTrainingList(long userId) {
        final Calendar startToday = Calendar.getInstance();
        startToday.set(HOUR_OF_DAY, 0);
        startToday.set(MINUTE, 0);
        startToday.set(SECOND, 0);
        startToday.set(MILLISECOND, 0);
        return userDataDAO.getById(userId)
                .getTrainingCollection()
                .stream()
                .filter(training ->
                        training.getTrainingDate().getTime() < startToday.getTimeInMillis())
                .collect(Collectors.toList());
    }
}















