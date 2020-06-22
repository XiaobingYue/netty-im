package com.yxb.handler.resp;

import com.yxb.protocol.resp.CreateGroupRespPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CreateGroupRespHandler extends SimpleChannelInboundHandler<CreateGroupRespPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                CreateGroupRespPacket createGroupRespPacket) throws Exception {
        System.out.print("群创建成功，id 为[" + createGroupRespPacket.getGroupId() + "], ");
        System.out.println("群里面有：" + createGroupRespPacket.getUserNameList());
    }
}
