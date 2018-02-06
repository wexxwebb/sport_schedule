package common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

public class FormattedAnnotationBeanPostProcessor implements BeanPostProcessor {

    private Logger logger = Logger.getLogger(FormattedAnnotationBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Formatted.class)) {
                if (field.getType().equals(Gson.class)) {
                    field.setAccessible(true);
                    String pattern = field.getAnnotation(Formatted.class).pattern();
                    try {
                        field.set(bean, new GsonBuilder().setDateFormat(pattern).create());
                    } catch (IllegalAccessException e) {
                        logger.error(e);
                    }
                } else {
                    throw new BeanInstantiationException(bean.getClass(),
                            "Illegal type annotated. Can be only com.google.Gson");
                }
            }
        }
        return bean;
    }
}
