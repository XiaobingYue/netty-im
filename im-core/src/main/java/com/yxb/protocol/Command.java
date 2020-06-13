package com.yxb.protocol;

import com.yxb.protocol.req.LoginReqPackage;
import com.yxb.protocol.req.MsgReqPacket;
import com.yxb.protocol.resp.LoginRespPacket;
import com.yxb.protocol.resp.MsgRespPacket;
import lombok.Getter;

@Getter
public enum  Command {
    LOGIN_REQUEST((byte) 1, LoginReqPackage.class),
    LOGIN_RESPONSE((byte) 2, LoginRespPacket.class),
    MSG_REQUEST((byte) 3, MsgReqPacket.class),
    MSG_RESPONSE((byte) 4, MsgRespPacket.class);

    private byte type;

    private Class<? extends Packet> clazz;

    Command(byte type, Class<? extends Packet> clazz) {
        this.type = type;
        this.clazz = clazz;
    }

    public static Class<? extends Packet> getCommandByType(byte type) {
        for (Command value : values()) {
            if (value.getType() == type) {
                return value.getClazz();
            }
        }
        return null;
    }
}
