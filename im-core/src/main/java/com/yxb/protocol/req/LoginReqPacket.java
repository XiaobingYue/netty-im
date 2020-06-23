package com.yxb.protocol.req;

import com.yxb.protocol.Command;
import com.yxb.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginReqPacket extends Packet {

    private String userId;

    private String username;

    private String password;
    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST.getType();
    }
}
