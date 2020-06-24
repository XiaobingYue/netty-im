package com.yxb.handler;

import com.yxb.handler.req.*;
import com.yxb.protocol.Command;
import com.yxb.protocol.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

import static com.yxb.protocol.Command.*;

@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMHandler INSTANCE = new IMHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private IMHandler() {
        handlerMap = new HashMap<>();

        handlerMap.put(MSG_REQUEST.getType(), MsgRequestHandler.INSTANCE);
        handlerMap.put(CREATE_GROUP_REQ.getType(), CreateGroupReqHandler.INSTANCE);
        handlerMap.put(JOIN_GROUP_REQ.getType(), JoinGroupReqHandler.INSTANCE);
        handlerMap.put(LEAVE_GROUP_REQ.getType(), LeaveGroupReqHandler.INSTANCE);
        handlerMap.put(LIST_GROUP_MEMBERS_REQ.getType(), ListGroupMembersReqHandler.INSTANCE);
        handlerMap.put(GROUP_MSG_REQ.getType(), GroupMsgReqHandler.INSTANCE);
        handlerMap.put(LOGOUT_REQ.getType(), LogoutReqHandler.INSTANCE);
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        handlerMap.get(msg.getCommand()).channelRead(ctx,msg );
    }
}
