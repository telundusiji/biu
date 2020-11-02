package site.teamo.biu.download.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * 类描述 动态加载外部jar包的自定义类加载器
 *
 * @version 1.0
 * @fileName ModuleClassLoader.java
 * @date 2019-06-21 10:22
 */

public class PluginClassLoader extends URLClassLoader {

    //属于本类加载器加载的jar包
    private JarFile[] jarFiles;

    //保存已经加载过的Class对象
    private Map<String, Class> cacheClassMap = new HashMap<>();

    private Map<String, byte[]> classBytesMap = new HashMap<>();

    //需要注册的spring bean的name集合
    private List<String> registeredBean = new ArrayList<>();


    //构造
    public PluginClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
        if (urls == null) {
            return;
        }
        jarFiles = new JarFile[urls.length];
        for (int i = 0; i < urls.length; i++) {
            try {
                jarFiles[i] = new JarFile(urls[i].getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //初始化类加载器执行类加载
        init();
    }


    //重写loadClass方法
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (findLoadedClass(name) == null) {
            return super.loadClass(name);
        } else {
            return cacheClassMap.get(name);
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class aClass = null;
        // 获取该class文件字节码数组
        byte[] classData = classBytesMap.get(name);

        if (classData != null) {
            // 将class的字节码数组转换成Class类的实例
            aClass = defineClass(name, classData, 0, classData.length);
        }
        return aClass;
    }

    /**
     * 方法描述 初始化类加载器，保存字节码
     *
     * @method init
     */
    private void init() {

        /**
         * 读取所有jar包的class
         */

        for (JarFile jarFile : jarFiles) {
            readClassByte(classBytesMap, jarFile);
        }

        /**
         * 将jar中的每一个class字节码进行Class载入
         */
        for (Map.Entry<String, byte[]> entry : classBytesMap.entrySet()) {
            String key = entry.getKey();
            Class<?> aClass = null;
            try {
                aClass = loadClass(key);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            cacheClassMap.put(key, aClass);
        }

    }

    /**
     * 解析jar包每一项
     *
     * @param jarFile
     */
    public void readClassByte(Map<String, byte[]> classBytesMap, JarFile jarFile) {
        Enumeration<JarEntry> en = jarFile.entries();
        while (en.hasMoreElements()) {
            JarEntry je = en.nextElement();
            String name = je.getName();
            if (!StringUtils.endsWithIgnoreCase(je.getName(), ".class")) {
                continue;
            }
            try (InputStream input = jarFile.getInputStream(je)) {
                String className = name.replace(".class", "").replaceAll("/", ".");
                byte[] classBytes = StreamUtils.copyToByteArray(input);
                classBytesMap.put(className, classBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 方法描述 初始化spring bean
     *
     * @method initBean
     */
    public void initBean() {
        for (Map.Entry<String, Class> entry : cacheClassMap.entrySet()) {
            String className = entry.getKey();
            Class<?> cla = entry.getValue();
            if (isSpringBeanClass(cla)) {
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(cla);
                BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
                //设置当前bean定义对象是单利的
                beanDefinition.setScope("singleton");

                //将变量首字母置小写
                String beanName = StringUtils.uncapitalize(className);

                beanName = beanName.substring(beanName.lastIndexOf(".") + 1);
                beanName = StringUtils.uncapitalize(beanName);

//                SpringContextUtil.getBeanFactory().registerBeanDefinition(beanName,beanDefinition);
                registeredBean.add(beanName);
                System.out.println("注册bean:" + beanName);
            }
        }
    }

    //获取当前类加载器注册的bean
    //在移除当前类加载器的时候需要手动删除这些注册的bean
    public List<String> getRegisteredBean() {
        return registeredBean;
    }


    /**
     * 方法描述 判断class对象是否带有spring的注解
     *
     * @param cla jar中的每一个class
     * @return true 是spring bean   false 不是spring bean
     * @method isSpringBeanClass
     */
    public boolean isSpringBeanClass(Class<?> cla) {
        if (cla == null) {
            return false;
        }
        //是否是接口
        if (cla.isInterface()) {
            return false;
        }

        //是否是抽象类
        if (Modifier.isAbstract(cla.getModifiers())) {
            return false;
        }

        if (cla.getAnnotation(Component.class) != null) {
            return true;
        }
        if (cla.getAnnotation(Repository.class) != null) {
            return true;
        }
        if (cla.getAnnotation(Service.class) != null) {
            return true;
        }

        return false;
    }


    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        URL url = new URL("file://D:\\code\\biu\\download-server\\target\\download-server-1.0-SNAPSHOT.jar");
        PluginClassLoader pluginClassLoader = new PluginClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
        Class<?> aClass = Class.forName("site.teamo.biu.download.util.SystemUtil", true, pluginClassLoader);
        Arrays.stream(aClass.getMethods()).forEach(method -> {
            if(method.getName().contains("getOS")){
                try {
                    System.out.println(method.invoke(aClass,null));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(aClass);

    }
}
