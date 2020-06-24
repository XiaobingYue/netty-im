package com.yxb.console;

import com.yxb.protocol.req.ListGroupMembersReqPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class ListGroupMembersConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入要查询的群聊ID：");
        String groupId = scanner.next();
        ListGroupMembersReqPacket reqPacket = new ListGroupMembersReqPacket();
        reqPacket.setGroupId(groupId);
        channel.writeAndFlush(reqPacket);
    }
}
