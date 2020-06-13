package com.yxb.serialize;

public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    byte getSerializerAlgorithm();

    byte[] serialize(Object o);

    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
