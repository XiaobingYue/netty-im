package com.yxb;

import com.yxb.codec.PacketDecoder;
import com.yxb.codec.PacketEncoder;
import com.yxb.console.ConsoleCommandManager;
import com.yxb.console.LoginConsoleCommand;
import com.yxb.handler.Spliter;
import com.yxb.handler.resp.*;
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
                        socketChannel.pipeline().addLast(new LogoutRespHandler());
                        socketChannel.pipeline().addLast(new MsgRespHandler());
                        socketChannel.pipeline().addLast(new CreateGroupRespHandler());
                        socketChannel.pipeline().addLast(new JoinGroupRespHandler());
                        socketChannel.pipeline().addLast(new LeaveGroupRespHandler());
                        socketChannel.pipeline().addLast(new ListGroupMembersRespHandler());
                        socketChannel.pipeline().addLast(new GroupMsgRespHandler());
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
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (SessionUtil.hasLogin(channel)) {
                    consoleCommandManager.exec(sc, channel);
                } else {
                    // 登陆
                    loginConsoleCommand.exec(sc,channel);
                }
            }
        }).start();
    }
}
