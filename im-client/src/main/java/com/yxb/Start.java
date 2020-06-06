package com.yxb;

import com.yxb.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author yxb
 * @since 2020/6/6
 */
public class Start {

    public static void main(String[] args) {
        NioEventLoopGroup work = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(work)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });
        bootstrap.connect("127.0.0.1",9999).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("客户端连接成功...");
            } else {
                System.out.println("客户端连接失败");
            }
        });
    }
}
