package com.zlht.pose.developer.remote.management.client;

import com.zlht.pose.developer.remote.management.Configuration.ManagementConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagementClient {

    @Autowired
    private ManagementConfiguration managementConfiguration;


    /**
     * 连通状态检查
     */
    public Boolean check() {
        String ip = managementConfiguration.getIp();
        int port = managementConfiguration.getPort();
        try (Socket socket = new Socket()) {
            InetSocketAddress endpoint = new InetSocketAddress(ip, port);
            socket.connect(endpoint, 1000); // 设置连接超时时间为1秒
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 发送请求
     */

    /**
     * 默认连接信息
     */
}
