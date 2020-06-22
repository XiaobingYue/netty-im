package com.yxb.console;

import com.yxb.protocol.req.MsgReqPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.println("请输入对方id: ");
        String toUserId = sc.next();
        System.out.println("请输入消息：");
        String message = sc.next();
        channel.writeAndFlush(new MsgReqPacket(message,toUserId));
    }
}
