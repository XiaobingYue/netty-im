package com.yxb.handler.req;

import com.yxb.protocol.req.CreateGroupRequestPacket;
import com.yxb.protocol.resp.CreateGroupRespPacket;
import com.yxb.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ChannelHandler.Sharable
public class CreateGroupReqHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupReqHandler INSTANCE = new CreateGroupReqHandler();

    protected CreateGroupReqHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();

        List<String> userNameList = new ArrayList<>();
        // 1. 创建一个 channel 分组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        // 2. 筛选出待加入群聊的用户的 channel 和 userName
        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }
        // 3. 创建群聊创建结果的响应
        CreateGroupRespPacket createGroupResponsePacket = new CreateGroupRespPacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(UUID.randomUUID().toString().replaceAll("-",""));
        createGroupResponsePacket.setUserNameList(userNameList);

        // 4. 给每个客户端发送拉群通知
        channelGroup.writeAndFlush(createGroupResponsePacket);
        SessionUtil.bindChannelGroup(createGroupResponsePacket.getGroupId(),channelGroup);
        System.out.print("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "], ");
        System.out.println("群里面有：" + createGroupResponsePacket.getUserNameList());

    }
}
