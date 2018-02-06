package common;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

public class LoggedAnnotationPostProcessor implements BeanPostProcessor {

    private static Logger logger = Logger.getLogger(LoggedAnnotationPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Logged.class)) {
                if (field.getType().equals(Logger.class)) {
                    try {
                        field.setAccessible(true);
                        field.set(bean, Logger.getLogger(bean.getClass()));
                    } catch (IllegalAccessException e) {
                        logger.error(e);
                    }
                } else {
                    throw new BeanInstantiationException(bean.getClass(),
                            "Illegal type annotated. Can be only org.apache.log4j.Logger");
                }
            }
        }
        return bean;
    }
}
