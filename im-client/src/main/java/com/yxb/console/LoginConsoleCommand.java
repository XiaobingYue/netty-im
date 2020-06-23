package com.yxb.console;

import com.yxb.attribute.Attributes;
import com.yxb.protocol.req.LoginReqPacket;
import io.netty.channel.Channel;

import java.util.Scanner;
import java.util.UUID;

public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel channel) {
        LoginReqPacket loginReqPacket = new LoginReqPacket();
        System.out.println("请输入您的昵称");
        String nickName = sc.next();
        loginReqPacket.setUsername(nickName);
        loginReqPacket.setUserId(UUID.randomUUID().toString().replaceAll("-",""));
        channel.writeAndFlush(loginReqPacket);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
