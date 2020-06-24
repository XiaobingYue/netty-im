package com.yxb.handler.req;

import com.yxb.protocol.req.LoginReqPacket;
import com.yxb.protocol.resp.LoginRespPacket;
import com.yxb.session.Session;
import com.yxb.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginReqPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    protected LoginRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginReqPacket loginReqPacket) throws Exception {
        LoginRespPacket loginRespPacket = login(loginReqPacket,channelHandlerContext.channel());
        channelHandlerContext.channel().writeAndFlush(loginRespPacket);
    }

    private LoginRespPacket login(LoginReqPacket loginReqPacket, Channel channel) {
        boolean b = SessionUtil.hasLogin(channel);
        LoginRespPacket respPacket = new LoginRespPacket();
        if (b) {
            Session session = SessionUtil.getSession(channel);
            System.out.println(session.getUserName()+"已经登陆，无需重复登陆");
            respPacket.setSuccess(false);
            respPacket.setReason("您已登陆，无需重复登陆");
        } else {
            System.out.println("["+loginReqPacket.getUsername()+"]登陆成功");
            respPacket.setSuccess(true);
            respPacket.setVersion(loginReqPacket.getVersion());
            respPacket.setUserId(loginReqPacket.getUserId());
            respPacket.setUserName(loginReqPacket.getUsername());
            Session session = new Session();
            session.setUserId(loginReqPacket.getUserId());
            session.setUserName(loginReqPacket.getUsername());
            SessionUtil.bindSession(session,channel);
        }
        return respPacket;
    }
}
