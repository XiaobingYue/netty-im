package com.yxb.handler.resp;

import com.yxb.protocol.resp.MsgRespPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MsgRespHandler extends SimpleChannelInboundHandler<MsgRespPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MsgRespPacket msgRespPacket) throws Exception {

        System.out.println("收到["+msgRespPacket.getFromUserName()+"]的消息：["+msgRespPacket.getMessage()+"]");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭");
        super.channelInactive(ctx);
    }
}
