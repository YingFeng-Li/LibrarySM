package cn.edu.guet.mvc;

/**
 * @Author liyingfeng
 * @Date 2021/6/27 10:57
 * @Version 1.0
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.URISyntaxException;
import java.util.Map;

@WebListener()
//上下文监听器
/*该监听器的作用是在启动tomcat的时候自动执行Configuration的config方法
* */
public class ContextConfigListener implements ServletContextListener{
    // Public constructor is required by servlet spec
    public ContextConfigListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
//        自动调用
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed).
         You can initialize servlet context related data here.
      */
        try {
            Map<String,ControllerMapping> controllerMapping=new Configuration().config();
            /*
            服务端给网页（jsp）传数据，把数据放入某个作用域：request、session、application
             */
            sce.getServletContext().setAttribute("cn.guet.web.controller",controllerMapping);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context
         (the Web application) is undeployed or
         Application Server shuts down.
      */
    }

}
