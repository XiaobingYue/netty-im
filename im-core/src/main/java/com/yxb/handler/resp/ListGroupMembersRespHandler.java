package com.yxb.handler.resp;

import com.yxb.protocol.resp.ListGroupMembersRespPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ListGroupMembersRespHandler extends SimpleChannelInboundHandler<ListGroupMembersRespPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRespPacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println("群组中有：["+String.join("、",msg.getUserNameList())+"]");
        } else {
            System.out.println("查询失败，原因："+msg.getReason());
        }
    }
}
