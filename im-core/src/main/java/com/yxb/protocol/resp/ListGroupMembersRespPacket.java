package com.yxb.protocol.resp;

import com.yxb.protocol.Command;
import com.yxb.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListGroupMembersRespPacket extends Packet {

    private List<String> userNameList;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESP.getType();
    }
}
