package com.yxb.protocol;

import com.yxb.serialize.Serializer;
import com.yxb.serialize.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;

public class PacketCodeC {

    private PacketCodeC() {
    }

    private static PacketCodeC INSTANCE = new PacketCodeC();

    public static PacketCodeC getInstance() {
        return INSTANCE;
    }

    public static final int MAGIC_NUMBER = 0x12345678;

    public void encode(ByteBuf byteBuf,Packet packet) {
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 魔法值
        byteBuf.writeInt(MAGIC_NUMBER);
        // 版本
        byteBuf.writeByte(packet.getVersion());

        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public Packet decode(ByteBuf byteBuf) {
        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);
        byte algorithm = byteBuf.readByte();
        byte command = byteBuf.readByte();
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Class<? extends Packet> clazz = Command.getCommandByType(command);
        return SerializerAlgorithm.fromType(algorithm).deserialize(clazz, bytes);
    }

}
