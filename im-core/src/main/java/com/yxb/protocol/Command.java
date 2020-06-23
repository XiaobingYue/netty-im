package com.yxb.protocol;

import com.yxb.protocol.req.CreateGroupRequestPacket;
import com.yxb.protocol.req.LoginReqPacket;
import com.yxb.protocol.req.LogoutReqPacket;
import com.yxb.protocol.req.MsgReqPacket;
import com.yxb.protocol.resp.CreateGroupRespPacket;
import com.yxb.protocol.resp.LoginRespPacket;
import com.yxb.protocol.resp.LogoutRespPacket;
import com.yxb.protocol.resp.MsgRespPacket;
import lombok.Getter;

@Getter
public enum  Command {
    LOGIN_REQUEST((byte) 1, LoginReqPacket.class),
    LOGIN_RESPONSE((byte) 2, LoginRespPacket.class),
    MSG_REQUEST((byte) 3, MsgReqPacket.class),
    MSG_RESPONSE((byte) 4, MsgRespPacket.class),
    CREATE_GROUP_REQ((byte) 5, CreateGroupRequestPacket.class),
    CREATE_GROUP_RESP((byte) 6, CreateGroupRespPacket.class),
    LOGOUT_REQ((byte) 7, LogoutReqPacket.class),
    LOGOUT_RESP((byte) 8, LogoutRespPacket.class);

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
