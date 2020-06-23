package com.yxb.console;

import com.yxb.protocol.req.LogoutReqPacket;
import com.yxb.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        if (SessionUtil.hasLogin(channel)) {
            LogoutReqPacket logoutReqPacket = new LogoutReqPacket();
            logoutReqPacket.setVersion((byte) 1);
            channel.writeAndFlush(logoutReqPacket);
        } else {
            System.out.println("未登陆无法注销");
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
