package com.yxb.handler.resp;

import com.yxb.protocol.resp.LeaveGroupRespPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LeaveGroupRespHandler extends SimpleChannelInboundHandler<LeaveGroupRespPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LeaveGroupRespPacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println("退出群聊成功");
        } else {
            System.out.println("退出群聊失败，原因："+msg.getReason());
        }
    }
}
