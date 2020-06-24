package com.yxb.handler.req;

import com.yxb.protocol.req.JoinGroupReqPacket;
import com.yxb.protocol.resp.JoinGroupRespPacket;
import com.yxb.session.Session;
import com.yxb.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

@ChannelHandler.Sharable
public class JoinGroupReqHandler extends SimpleChannelInboundHandler<JoinGroupReqPacket> {

    public static final JoinGroupReqHandler INSTANCE = new JoinGroupReqHandler();

    protected JoinGroupReqHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupReqPacket msg) throws Exception {
        Channel channel = ctx.channel();
        JoinGroupRespPacket joinGroupRespPacket = new JoinGroupRespPacket();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(msg.getGroupId());
        if (channelGroup == null) {
            joinGroupRespPacket.setSuccess(false);
            joinGroupRespPacket.setReason("加入群组失败，群组不存在");
        } else {
            channelGroup.add(channel);
            List<String> userNameList = new ArrayList<>();
            channelGroup.forEach(channel1 -> {
                Session session = SessionUtil.getSession(channel1);
                userNameList.add(session.getUserName());
            });
            joinGroupRespPacket.setSuccess(true);
            joinGroupRespPacket.setUserNameList(userNameList);
        }
        ctx.writeAndFlush(joinGroupRespPacket);
    }
}
