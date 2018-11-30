package tcp;

import java.util.HashMap;
import java.util.Map;

/**
 * tcp客户端管理者
 * 这里说的客户端应该是回应发消息给设备的
 */
public class TCPManager {


    /**
     * tcp客户端端集
     */
    static volatile Map<String, TCPClient> tcpClientMap = new HashMap();
    /**
     * 内部单例持有类
     */
    private static class SingletonHolder {
        private static TCPManager instance = new TCPManager();
        private SingletonHolder() {}
    }
    /**
     * 返回一个tcp客户端
     *
     * @return
     */
    public static TCPManager getInstance() {
        return TCPManager.SingletonHolder.instance;
    }
    /**
     * 得到一个客户端
     * @param key 这里的key为：ip#port 例如：192.168.10.1#5001
     * @return 返回的是一个已经连接的客户端
     */
    public TCPClient getTcpClient(String key) {
        //直接创建好WebClient，并且检测连接，已经连接后直接返回，没有连接的时候就在此方法连接
        TCPClient tcpClient = null;
        if (tcpClientMap.get(key) == null) {//没有这个客户端连接
            String[] infos = key.split("#");//这里的key为：ip#port 例如：192.168.10.1#5001
            tcpClient = new TCPClient(infos[0], Integer.valueOf(infos[1]));//就根据该key创建一个客户端
            tcpClient.connect();//连接该客户端
            tcpClientMap.put(key, tcpClient);//添加到map里
        } else {
            tcpClient = tcpClientMap.get(key);
            if (!tcpClient.isconnect()) tcpClient.reconnect();//如果没有连接就连接起来
        }
        return tcpClient;
    }

    /**
     * 关闭所有的客户端,
     */
    public void clearWebClient() {
        for (TCPClient TCPClient : tcpClientMap.values()) TCPClient.close();
        tcpClientMap.clear();
    }
    /**
     * 得到客户端的集
     * @return
     */
    public Map<String, TCPClient> getTcpClientMap() {
        return tcpClientMap;
    }
}
