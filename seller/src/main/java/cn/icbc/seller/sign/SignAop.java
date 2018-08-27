package cn.icbc.seller.sign;

import cn.icbc.seller.service.OrderService;
import cn.icbc.seller.service.SignService;
import cn.icbc.util.RSAUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @Auther: asus
 * @Date: 2018/8/27 17:59
 */
@Aspect
@Component
public class SignAop {

    private static Logger LOG = LoggerFactory.getLogger(SignAop.class);

    @Autowired
    private SignService signService;

    @Before(value = "execution(* cn.icbc.seller.controller..*.*(..))&& args(authId,sign,text,..)")
    public void verify(String authId, String sign, SignText text) {
        String publicKey = signService.getPublicKeyByAuthId(authId);


        Assert.isTrue(RSAUtil.verify(text.toText(), sign, publicKey), "验签失败");
    }
}
