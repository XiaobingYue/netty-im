package com.yxb.handler.resp;

import com.yxb.protocol.resp.LogoutRespPacket;
import com.yxb.session.Session;
import com.yxb.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogoutRespHandler extends SimpleChannelInboundHandler<LogoutRespPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                LogoutRespPacket logoutRespPacket) throws Exception {
        if (logoutRespPacket.isSuccess()) {
            SessionUtil.unBindSession(channelHandlerContext.channel());
            System.out.println("注销成功");

        } else {
            System.out.println("注销失败："+logoutRespPacket.getReason());
        }
    }
}
