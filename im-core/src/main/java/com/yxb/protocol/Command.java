package com.yxb.protocol;

import com.yxb.protocol.req.*;
import com.yxb.protocol.resp.*;
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
    LOGOUT_RESP((byte) 8, LogoutRespPacket.class),
    JOIN_GROUP_REQ((byte) 9, JoinGroupReqPacket.class),
    JOIN_GROUP_RESP((byte) 10, JoinGroupRespPacket.class),
    LEAVE_GROUP_REQ((byte) 11, LeaveGroupReqPacket.class),
    LEAVE_GROUP_RESP((byte) 12, LeaveGroupRespPacket.class),
    LIST_GROUP_MEMBERS_REQ((byte) 13, ListGroupMembersReqPacket.class),
    LIST_GROUP_MEMBERS_RESP((byte) 14, ListGroupMembersRespPacket.class),
    GROUP_MSG_REQ((byte) 15, GroupMsgReqPacket.class),
    GROUP_MSG_RESP((byte) 16, GroupMsgRespPacket.class);

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
