package services.impl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services._inter.DashboardService;

public class DashboardServiceImplTest {

    private static ApplicationContext context;

    private DashboardService dashboardService;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext(
                "database.xml", "spring-config.xml");
        dashboardService = context.getBean(DashboardServiceImpl.class);
    }

    @Test
    public void getTodayTrainingList() {

        dashboardService.getPastTrainingList(1).forEach(System.out::println);
        System.out.println("===");
        dashboardService.getTodayTrainingList(1).forEach(System.out::println);
        System.out.println("===");
        dashboardService.getFutureTrainingList(1).forEach(System.out::println);


    }
}