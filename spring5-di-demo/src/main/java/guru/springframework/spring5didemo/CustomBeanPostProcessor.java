//: guru.springframework.spring5didemo.CustomBeanPostProcessor.java

package guru.springframework.spring5didemo;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof LifeCycleDemoBean) {
            ((LifeCycleDemoBean) bean).beforeInit();
        }

        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof LifeCycleDemoBean) {
            ((LifeCycleDemoBean) bean).afterInit();
        }

        return bean;
    }

}///~