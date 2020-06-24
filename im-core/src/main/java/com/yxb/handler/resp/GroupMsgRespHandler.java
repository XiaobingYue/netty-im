package com.yxb.handler.resp;

import com.yxb.protocol.resp.GroupMsgRespPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GroupMsgRespHandler extends SimpleChannelInboundHandler<GroupMsgRespPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMsgRespPacket msg) throws Exception {
        System.out.println("收到群["+msg.getFromGroupId()
                +"]中["+msg.getFromUserName()
                +"]发送的消息：["+msg.getMessage()+"]");
    }
}
