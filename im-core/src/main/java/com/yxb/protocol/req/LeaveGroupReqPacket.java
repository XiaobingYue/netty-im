package com.yxb.protocol.req;

import com.yxb.protocol.Command;
import com.yxb.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LeaveGroupReqPacket extends Packet {

    private String groupId;
    @Override
    public Byte getCommand() {
        return Command.LEAVE_GROUP_REQ.getType();
    }
}
