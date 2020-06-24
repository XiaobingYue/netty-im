package com.yxb.handler.req;

import com.yxb.protocol.req.LeaveGroupReqPacket;
import com.yxb.protocol.resp.LeaveGroupRespPacket;
import com.yxb.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class LeaveGroupReqHandler extends SimpleChannelInboundHandler<LeaveGroupReqPacket> {

    public static final LeaveGroupReqHandler INSTANCE = new LeaveGroupReqHandler();

    protected LeaveGroupReqHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LeaveGroupReqPacket msg) throws Exception {
        Channel channel = ctx.channel();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(msg.getGroupId());
        channelGroup.remove(channel);
        LeaveGroupRespPacket respPacket = new LeaveGroupRespPacket();
        respPacket.setSuccess(true);
        ctx.writeAndFlush(respPacket);
    }
}
