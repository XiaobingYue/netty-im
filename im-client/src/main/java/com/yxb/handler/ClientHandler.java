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
        for (int i = 0; i < 1000; i++) {
            ByteBuf byteBuf = ctx.alloc().buffer();
            byteBuf.writeBytes("你好服务端...".getBytes(StandardCharsets.UTF_8));
            ctx.channel().writeAndFlush(byteBuf);
        }
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("接收到服务端数据 -> "+byteBuf.toString(StandardCharsets.UTF_8));
        super.channelRead(ctx, msg);
    }
}
