package com.yxb.protocol.resp;

import com.yxb.protocol.Command;
import com.yxb.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class JoinGroupRespPacket extends Packet {

    private Boolean success;

    private String reason;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESP.getType();
    }
}
