package com.yxb.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @author yxb
 * @since 2020/6/6
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("开始向服务端发送消息...");
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes("你好服务端...".getBytes(StandardCharsets.UTF_8));
        ctx.channel().writeAndFlush(byteBuf);
        //super.channelActive(ctx);
    }
}
