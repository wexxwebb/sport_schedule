package common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

public class LoggedAnnotationPostProcessor implements BeanPostProcessor {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoggedAnnotationPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Logged.class)) {
                try {
                    field.setAccessible(true);
                    field.set(bean, org.apache.log4j.Logger.getLogger(bean.getClass()));
                } catch (IllegalAccessException e) {
                    logger.error(new Log(
                            String.format("Can't set 'logger' at bean %s, because field 'logger' not accessible",
                                    beanName), e));
                }
            }
        }
        return bean;
    }
}
