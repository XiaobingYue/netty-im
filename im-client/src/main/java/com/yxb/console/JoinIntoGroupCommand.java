package com.yxb.console;

import com.yxb.protocol.req.JoinGroupReqPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class JoinIntoGroupCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入加入群组的ID：");
        String groupId = scanner.next();
        JoinGroupReqPacket reqPacket = new JoinGroupReqPacket();
        reqPacket.setGroupId(groupId);
        channel.writeAndFlush(reqPacket);
    }
}
