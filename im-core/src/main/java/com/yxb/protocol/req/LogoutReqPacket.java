package com.yxb.protocol.req;

import com.yxb.protocol.Command;
import com.yxb.protocol.Packet;

public class LogoutReqPacket extends Packet {


    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQ.getType();
    }
}
