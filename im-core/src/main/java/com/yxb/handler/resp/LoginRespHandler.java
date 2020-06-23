package com.yxb.handler.resp;

import com.yxb.attribute.Attributes;
import com.yxb.protocol.resp.LoginRespPacket;
import com.yxb.session.Session;
import com.yxb.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginRespHandler extends SimpleChannelInboundHandler<LoginRespPacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        LoginReqPackage loginReqPackage = new LoginReqPackage();
//        loginReqPackage.setUserId(UUID.randomUUID().toString());
//        loginReqPackage.setUsername("yxb");
//        loginReqPackage.setPassword("111");
//        ctx.channel().writeAndFlush(loginReqPackage);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRespPacket loginRespPacket) throws Exception {
        if (loginRespPacket.isSuccess()){
            System.out.println("登陆成功,欢迎您："+loginRespPacket.getUserName()+",您的ID："+loginRespPacket.getUserId());
            Session session = new Session();
            session.setUserId(loginRespPacket.getUserId());
            session.setUserName(loginRespPacket.getUserName());
            SessionUtil.bindSession(session,channelHandlerContext.channel());
        } else {
            System.out.println("登陆失败："+loginRespPacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭");
    }
}
