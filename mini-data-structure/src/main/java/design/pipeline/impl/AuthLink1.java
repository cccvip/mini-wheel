package design.pipeline.impl;

import design.pipeline.AuthInfo;
import design.pipeline.AuthLink;

import java.util.Date;

public class AuthLink1 extends AuthLink {
    public AuthLink1(String levelUserId, String levelUserName) {
        super(levelUserId, levelUserName);
    }

    @Override
    public AuthInfo doAuth(String uId, String orderId, Date authDate) {
        // do
        AuthLink  next = super.next();
        return next.doAuth(uId,orderId,authDate);
    }
}
