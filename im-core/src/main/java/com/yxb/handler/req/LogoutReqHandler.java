package com.yxb.handler.req;

import com.yxb.handler.AuthHandler;
import com.yxb.protocol.req.LogoutReqPacket;
import com.yxb.protocol.resp.LogoutRespPacket;
import com.yxb.session.Session;
import com.yxb.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogoutReqHandler extends SimpleChannelInboundHandler<LogoutReqPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                LogoutReqPacket logoutReqPacket) throws Exception {
        Channel channel = channelHandlerContext.channel();
        LogoutRespPacket logoutRespPacket = new LogoutRespPacket();
        if (SessionUtil.hasLogin(channel)) {
            Session session = SessionUtil.getSession(channel);
            // 注销
            SessionUtil.unBindSession(channel);
            channelHandlerContext.pipeline().addAfter("loginRequestHandler","authHandler",new AuthHandler());
            System.out.println("用户["+session.getUserName()+"]注销成功");
            logoutRespPacket.setSuccess(true);
        } else {
            logoutRespPacket.setSuccess(false);
            logoutRespPacket.setReason("未登陆，无法注销");
        }
        channelHandlerContext.channel().writeAndFlush(logoutRespPacket);
    }
}
