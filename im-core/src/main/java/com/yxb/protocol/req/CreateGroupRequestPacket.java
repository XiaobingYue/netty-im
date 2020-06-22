package com.yxb.protocol.req;

import com.yxb.protocol.Command;
import com.yxb.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQ.getType();
    }
}
