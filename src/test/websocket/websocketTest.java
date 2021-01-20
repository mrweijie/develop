package test.websocket;

import java.util.Scanner;

public class websocketTest {
    public static void main(String[] args) {
        BrowserServer SZMServer = WebManager.getWebServerByName("1");//中控,连接浏览器
        try {
            SZMServer.run(5432);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true){
            Scanner a  = new Scanner(System.in);
            String  str = a.nextLine();
            if("1".equals(str)){
                System.out.println(WebManager.map);
                System.out.println(WebManager.channelGroupMap.get("1"));
            }
        }
    }
}
