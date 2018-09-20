//: guru.springframework.spring5didemo.LifeCycleDemoBean.java

package guru.springframework.spring5didemo;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class LifeCycleDemoBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    public LifeCycleDemoBean() {
        System.out.println("## I'm in the LifeCycleBean's constructor.");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("## LifeCycleBean has been constructed.");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("## LifeCycleBean is going to be destroyed.");
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("## My bean name is: " + beanName);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("## My bean factory has been set here. ");
        //        System.out.println(beanFactory.);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        System.out.println("## ApplicationContext has been set here.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("## The LifeCycleBean has its properties set!");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("## The LifeCycleBean has been terminated.");
    }

    public void beforeInit() {
        System.out.println("## - Before Init - Called by BeanPostProcessor.");
    }

    public void afterInit() {
        System.out.println("## - After Init - Called by BeanPostProcessor.");
    }

}///~