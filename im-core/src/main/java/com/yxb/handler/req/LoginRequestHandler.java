package com.yxb.handler.req;

import com.yxb.attribute.Attributes;
import com.yxb.protocol.req.LoginReqPackage;
import com.yxb.protocol.resp.LoginRespPacket;
import com.yxb.session.Session;
import com.yxb.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginReqPackage> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginReqPackage loginReqPackage) throws Exception {
        LoginRespPacket loginRespPacket = login(loginReqPackage,channelHandlerContext.channel());
        channelHandlerContext.channel().writeAndFlush(loginRespPacket);
    }

    private LoginRespPacket login(LoginReqPackage loginReqPackage, Channel channel) {
        LoginRespPacket respPacket = new LoginRespPacket();
        respPacket.setSuccess(true);
        respPacket.setVersion(loginReqPackage.getVersion());
        respPacket.setUserId(loginReqPackage.getUserId());
        respPacket.setUserName(loginReqPackage.getUsername());
        Session session = new Session();
        session.setUserId(loginReqPackage.getUserId());
        session.setUserName(loginReqPackage.getUsername());
        SessionUtil.bindSession(session,channel);
        return respPacket;
    }
}
