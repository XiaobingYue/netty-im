package com.yxb.attribute;

import com.yxb.session.Session;
import io.netty.util.AttributeKey;

public class Attributes {

    public static final AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
