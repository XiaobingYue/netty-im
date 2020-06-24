package com.yxb.console;

import com.yxb.protocol.req.GroupMsgReqPacket;
import com.yxb.protocol.req.MsgReqPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.println("请输入群聊id: ");
        String groupId = sc.next();
        System.out.println("请输入消息：");
        String message = sc.next();
        GroupMsgReqPacket reqPacket = new GroupMsgReqPacket();
        reqPacket.setGroupId(groupId);
        reqPacket.setMessage(message);
        channel.writeAndFlush(reqPacket);
    }
}
