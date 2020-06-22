package com.yxb.console;

import com.yxb.protocol.req.LoginReqPackage;
import io.netty.channel.Channel;

import java.util.Scanner;
import java.util.UUID;

public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel channel) {
        LoginReqPackage loginReqPackage = new LoginReqPackage();
        System.out.println("请输入您的昵称");
        String nickName = sc.next();
        loginReqPackage.setUsername(nickName);
        loginReqPackage.setUserId(UUID.randomUUID().toString().replaceAll("-",""));
        channel.writeAndFlush(loginReqPackage);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
