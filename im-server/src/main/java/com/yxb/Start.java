package com.yxb;

import com.yxb.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
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
                    protected void initChannel(NioServerSocketChannel nioServerSocketChannel) {
                        System.out.println("服务端启动中...");
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childOption(ChannelOption.TCP_NODELAY,true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel nioSocketChannel) {
                        nioSocketChannel.pipeline().addLast(new ServerHandler());
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
