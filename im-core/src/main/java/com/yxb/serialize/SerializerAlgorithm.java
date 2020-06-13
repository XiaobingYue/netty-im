package com.yxb.serialize;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum SerializerAlgorithm {

    JSON((byte) 1,new JSONSerializer());


    private byte type;

    private Serializer serializer;

    SerializerAlgorithm(byte type, Serializer serializer) {
        this.type = type;
        this.serializer = serializer;
    }


    private static final Map<Byte,Serializer> serializers = new HashMap<>();

    static {
        for (SerializerAlgorithm value : SerializerAlgorithm.values()) {
            serializers.put(value.getType(),value.getSerializer());
        }
    }

    public static Serializer fromType(byte type) {
        return serializers.get(type);
    }
}
