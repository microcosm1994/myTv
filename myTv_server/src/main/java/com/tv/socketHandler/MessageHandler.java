package com.tv.socketHandler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.tv.config.SocketConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    private SocketIOServer socketIOServer;

    private static final Logger logger = LoggerFactory.getLogger(SocketConfig.class);

    @Autowired
    public MessageHandler(SocketIOServer server) {
        this.socketIOServer = server;
    }

    //添加connect事件，当客户端发起连接时调用
    @OnConnect
    public void onConnect(SocketIOClient client) {
        if (client != null) {
            String username = client.getHandshakeData().getSingleUrlParam("username");
            String password = client.getHandshakeData().getSingleUrlParam("password");
            String sessionId = client.getSessionId().toString();
            logger.info("连接成功, username=" + username + ", password=" + password + ", sessionId=" + sessionId);
            client.sendEvent("CONNECT", 0);
        } else {
            logger.error("客户端为空");
        }
    }

    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        logger.info("客户端断开连接, sessionId=" + client.getSessionId().toString());
        client.disconnect();
    }

    // 消息接收入口
    @OnEvent(value = "chatevent")
    public void onEvent(SocketIOClient client, AckRequest ackRequest, String chat) {
        logger.info("接收到客户端消息");
        if (ackRequest.isAckRequested()) {
            // send ack response with data to client
            ackRequest.sendAckData("服务器回答chatevent, userName=" + chat + ",message=" + chat);
        }
    }

    // 登录接口
//    @OnEvent(value = "login")
//    public void onLogin(SocketIOClient client, AckRequest ackRequest, LoginRequest message) {
//        logger.info("接收到客户端登录消息");
//        if (ackRequest.isAckRequested()) {
//            // send ack response with data to client
//            ackRequest.sendAckData("服务器回答login", message.getCode(), message.getBody());
//        }
//    }

}
