package main.java.tcp;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
