package com.yxb.protocol.req;

import com.yxb.protocol.Command;
import com.yxb.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMsgReqPacket extends Packet {

    private String message;

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MSG_REQ.getType();
    }
}
