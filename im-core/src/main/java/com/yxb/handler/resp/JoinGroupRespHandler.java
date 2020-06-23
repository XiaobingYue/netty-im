package com.yxb.handler.resp;

import com.yxb.protocol.resp.JoinGroupRespPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class JoinGroupRespHandler extends SimpleChannelInboundHandler<JoinGroupRespPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRespPacket msg) throws Exception {
        if (msg.getSuccess()) {
            System.out.println("加入群聊成功，群里面有：["+String.join(",",msg.getUserNameList())+"]");
        } else {
            System.out.println("加入群聊失败，原因："+msg.getReason());
        }
    }
}
