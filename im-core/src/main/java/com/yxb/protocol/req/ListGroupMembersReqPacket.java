package com.yxb.protocol.req;

import com.yxb.protocol.Command;
import com.yxb.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListGroupMembersReqPacket extends Packet {

    private String groupId;
    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQ.getType();
    }
}
