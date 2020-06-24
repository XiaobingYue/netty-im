package com.yxb.handler.req;

import com.yxb.protocol.req.GroupMsgReqPacket;
import com.yxb.protocol.resp.GroupMsgRespPacket;
import com.yxb.session.Session;
import com.yxb.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class GroupMsgReqHandler extends SimpleChannelInboundHandler<GroupMsgReqPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMsgReqPacket msg) throws Exception {
        Channel channel = ctx.channel();
        Session session = SessionUtil.getSession(channel);
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(msg.getGroupId());
        GroupMsgRespPacket respPacket = new GroupMsgRespPacket();
        respPacket.setFromGroupId(msg.getGroupId());
        respPacket.setFromUserId(session.getUserId());
        respPacket.setFromUserName(session.getUserName());
        respPacket.setMessage(msg.getMessage());
        channelGroup.writeAndFlush(respPacket);
    }
}
