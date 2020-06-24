package com.yxb.handler.req;

import com.yxb.protocol.req.ListGroupMembersReqPacket;
import com.yxb.protocol.resp.ListGroupMembersRespPacket;
import com.yxb.session.Session;
import com.yxb.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

public class ListGroupMembersReqHandler extends SimpleChannelInboundHandler<ListGroupMembersReqPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersReqPacket msg) throws Exception {
        ListGroupMembersRespPacket respPacket = new ListGroupMembersRespPacket();
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        if (channelGroup == null) {
            respPacket.setSuccess(false);
            respPacket.setReason("群聊不存在");
        } else {
            respPacket.setSuccess(true);
            List<String> userNameList = new ArrayList<>();
            channelGroup.forEach(channel -> {
                Session session = SessionUtil.getSession(channel);
                userNameList.add(session.getUserName());
            });
            respPacket.setUserNameList(userNameList);
        }
        ctx.channel().writeAndFlush(respPacket);

    }
}
