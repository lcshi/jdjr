package Common.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.NoSuchElementException;

/**
 * Created by shilc on 2016/3/15.
 */
public class SpringUtils implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;//spring 上下文
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        SpringUtils.beanFactory=configurableListableBeanFactory;
    }

    /**
     * 获取Name相同的Bean
     * @param name
     * @param <T>
     * @return bean
     * @throws BeansException
     */
    @SuppressWarnings("unchecked")
    public static <T>T GetBean(String name) throws BeansException{
        return (T)beanFactory.getBean(name);
    }

    /**
     * 获取类类型相同对象
     * @param clz
     * @param <T>
     * @return
     * @throws BeansException
     */
    @SuppressWarnings("unchecked")
    public static <T>T GetBean(Class<T> clz) throws  BeansException
    {
        T result=(T)beanFactory.getBean(clz);
        return result;
    }

    /**
     * 判断是否包含Name的对象
     * @param name
     * @return boolean
     */
    public static boolean ContainsBean(String name)
    {
        return beanFactory.containsBean(name);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static boolean IsSingleton(String name) throws NoSuchBeanDefinitionException{
        return beanFactory.isSingleton(name);
    }

    /**
     * 获取注册对象的类型
     * @param name
     * @return Class 注册对象的类型
     * @throws NoSuchBeanDefinitionException
     */
    public static Class<?> GetType(String name) throws NoSuchBeanDefinitionException{
        return beanFactory.getType(name);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static String[] GetAliases(String name) throws NoSuchBeanDefinitionException{
        return  beanFactory.getAliases(name);
    }
}
