package cn.edu.guet.mvc;

import cn.edu.guet.mvc.annotation.Controller;
import cn.edu.guet.mvc.annotation.RequestMapping;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Configuration {
    public Map<String, ControllerMapping> config() throws URISyntaxException {

        Map<String, ControllerMapping> controllerMapping = new HashMap<String, ControllerMapping>();
/*
* 运行完之后bundle便代表config.properties文件
* */
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        /*
        获取控制器包名称
        */
        String controllerPackageName = bundle.getString("controller.package");
        System.out.println("读取到的控制器包的名称为" + controllerPackageName);

        /*
        将控制包转换为具体路径,为了得到.class文件
        */
        String path = controllerPackageName.replace(".", "/");
        URI uri = Configuration.class.getResource("/" + path).toURI();
        File controllerDirectory = new File(uri);
        System.out.println(uri.toString());

        /*
        筛选出路径下所有的.class文件和全类名
         */
        String[] controllerFileNames = controllerDirectory.list();

        for (String className : controllerFileNames) {
            if (className.endsWith(".class")) {
                System.out.println("包下的所有class: " + className);// 结果，如：UserController.class
                    //全限定类名=包名 + 类名
                    String fullClassName = controllerPackageName + "." + StringUtils.substringBefore(className, ".class");
                    System.out.println("class下的全类名:"+fullClassName);
                try {
                    Class controllerClass = Class.forName(fullClassName);
                /*
                如果clazz中有Controller注解，才进一步处理
                 */
                    if (controllerClass.isAnnotationPresent(Controller.class)) {
                        System.out.println("这个类有controller注解：" + controllerClass.getSimpleName());
                       /* Method[] methods  = Controller.class.getMethods();
                        Annotation[] annotations = methods[0].getDeclaredAnnotations();
                        System.out.println(annotations[0].toString());*/
                        /*Method methods[] = MethodUtils.getMethodsWithAnnotation(controllerClass, RequestMapping.class);
                        for (Method method : methods) {
                            System.out.println("method方法的名字：" + method.getName());
                            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                            System.out.println("注解的值：" + annotation.value());
                        }*/
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                }
        }
        return controllerMapping;
    }
}
