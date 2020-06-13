package com.yxb;

import com.yxb.codec.PacketDecoder;
import com.yxb.codec.PacketEncoder;
import com.yxb.handler.Spliter;
import com.yxb.handler.resp.LoginRespHandler;
import com.yxb.handler.resp.MsgRespHandler;
import com.yxb.protocol.req.LoginReqPackage;
import com.yxb.protocol.req.MsgReqPacket;
import com.yxb.session.Session;
import com.yxb.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;
import java.util.UUID;

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
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(new Spliter());
                        socketChannel.pipeline().addLast(new PacketDecoder());
                        socketChannel.pipeline().addLast(new LoginRespHandler());
                        socketChannel.pipeline().addLast(new MsgRespHandler());
                        socketChannel.pipeline().addLast(new PacketEncoder());
                    }
                });
        bootstrap.connect("127.0.0.1",9999).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("客户端连接成功...");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleInput(channel);
                System.out.println("启用控制台输出线程...");
            } else {
                System.out.println("客户端连接失败");
            }
        });
    }

    private static void startConsoleInput(Channel channel) {
        Scanner sc = new Scanner(System.in);
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (SessionUtil.hasLogin(channel)) {
                    System.out.println("请输入对方id: ");
                    String toUserId = sc.next();
                    System.out.println("请输入消息：");
                    String message = sc.next();
                    channel.writeAndFlush(new MsgReqPacket(message,toUserId));
                } else {
                    // 登陆
                    LoginReqPackage loginReqPackage = new LoginReqPackage();
                    System.out.println("请输入您的昵称");
                    String nickName = sc.next();
                    loginReqPackage.setUsername(nickName);
                    loginReqPackage.setUserId(UUID.randomUUID().toString().replaceAll("-",""));
                    channel.writeAndFlush(loginReqPackage);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
