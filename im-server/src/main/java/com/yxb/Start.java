package com.yxb;

import com.yxb.codec.PacketDecoder;
import com.yxb.codec.PacketEncoder;
import com.yxb.handler.AuthHandler;
import com.yxb.handler.IMHandler;
import com.yxb.handler.PacketCodecHandler;
import com.yxb.handler.Spliter;
import com.yxb.handler.req.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author yxb
 * @since 2020/6/6
 */
public class Start {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel nioServerSocketChannel) {
                        System.out.println("服务端启动中...");
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childOption(ChannelOption.TCP_NODELAY,true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) {
                        nioSocketChannel.pipeline().addLast(new Spliter());
                        nioSocketChannel.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        nioSocketChannel.pipeline().addLast("loginRequestHandler", LoginRequestHandler.INSTANCE);
                        nioSocketChannel.pipeline().addLast("authHandler", AuthHandler.INSTANCE);
                        nioSocketChannel.pipeline().addLast( IMHandler.INSTANCE);
                    }
                });
        serverBootstrap.bind(9999).sync().addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("服务端启动成功");
            } else {
                System.out.println("服务端启动失败");
            }
        });
    }
}
