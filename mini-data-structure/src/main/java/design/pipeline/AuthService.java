package design.pipeline;

import design.pipeline.impl.AuthLink1;
import design.pipeline.impl.AuthLink2;

public class AuthService {
    public static void main(String[] args) {
        //责任链路
        AuthLink authLink = new AuthLink2("1000013", "王工")
                .appendNext(new AuthLink1("1000012", "张经理"));
    }

}
