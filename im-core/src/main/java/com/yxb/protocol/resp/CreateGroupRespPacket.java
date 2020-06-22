package com.yxb.protocol.resp;

import com.yxb.protocol.Command;
import com.yxb.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateGroupRespPacket extends Packet {

    private boolean success;

    private String groupId;

    private List<String> userNameList;
    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESP.getType();
    }
}
