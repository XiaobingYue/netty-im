package com.yxb.handler.req;

import com.yxb.protocol.req.MsgReqPacket;
import com.yxb.protocol.resp.MsgRespPacket;
import com.yxb.session.Session;
import com.yxb.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class MsgRequestHandler extends SimpleChannelInboundHandler<MsgReqPacket> {

    public static final MsgRequestHandler INSTANCE = new MsgRequestHandler();

    protected MsgRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MsgReqPacket msgReqPacket) throws Exception {
        System.out.println("收到客户端消息:["+msgReqPacket.getMessage()+"]");
        MsgRespPacket respPacket = new MsgRespPacket();
        Session session = SessionUtil.getSession(channelHandlerContext.channel());
        Channel channel = SessionUtil.getChannel(msgReqPacket.getToUserId());
        if (channel == null) {
            System.out.println("对方未在线");
        } else {
            respPacket.setFromUserId(session.getUserId());
            respPacket.setFromUserName(session.getUserName());
            respPacket.setMessage(msgReqPacket.getMessage());
            channel.writeAndFlush(respPacket);
        }
//        channelHandlerContext.channel().writeAndFlush(respPacket);
    }


}
