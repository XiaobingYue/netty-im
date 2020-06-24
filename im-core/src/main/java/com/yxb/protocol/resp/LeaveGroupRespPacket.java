package com.yxb.protocol.resp;

import com.yxb.protocol.Command;
import com.yxb.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LeaveGroupRespPacket extends Packet {

    private boolean success;

    private String reason;
    @Override
    public Byte getCommand() {
        return Command.LEAVE_GROUP_RESP.getType();
    }
}
