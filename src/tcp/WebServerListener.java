package tcp;


import cn.entity.Book;
import cn.service.BookService;
import cn.service.DynamicProxyHandler;
import cn.service.Impl.BookServiceImpl;
import org.omg.PortableServer.DynamicImplementation;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.reflect.Proxy;

/**
 * webserver的监听者
 */
public class WebServerListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BrowserServer av3000MiniPreviewServer = WebManager.getWebServerByName("3200");//av3000Mini预览 连接浏览器
        try {
            av3000MiniPreviewServer.run(6013);//预览
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 项目容器关闭时
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        TCPManager.getInstance().clearWebClient();//关闭TCP客户端
    }

}
