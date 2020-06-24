package com.yxb.console;

import com.yxb.protocol.req.LeaveGroupReqPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LeaveGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入退出群聊的ID：");
        String groupId = scanner.next();
        LeaveGroupReqPacket reqPacket = new LeaveGroupReqPacket();
        reqPacket.setGroupId(groupId);
        channel.writeAndFlush(reqPacket);
    }
}
