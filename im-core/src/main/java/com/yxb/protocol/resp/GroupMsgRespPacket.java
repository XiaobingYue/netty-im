package com.yxb.protocol.resp;

import com.yxb.protocol.Command;
import com.yxb.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMsgRespPacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String fromGroupId;

    private String message;
    @Override
    public Byte getCommand() {
        return Command.GROUP_MSG_RESP.getType();
    }
}
