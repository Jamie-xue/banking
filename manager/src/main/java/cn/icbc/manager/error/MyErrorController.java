package cn.icbc.manager.error;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Auther: asus
 * @Date: 2018/8/25 14:58
 */
public class MyErrorController extends BasicErrorController {
    public MyErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }


    @Override
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
         Map<String, Object> attrs = super.getErrorAttributes(request, includeStackTrace);

         attrs.remove("status");
         attrs.remove("error");
         attrs.remove("exception");
         attrs.remove("path");

        String code =(String) attrs.get("message");
        ErrorEnum errorEnum = ErrorEnum.getByCode(code);
        attrs.put("code",errorEnum.getCode());
        attrs.put("message",errorEnum.getMessage());
        attrs.put("canRetry",errorEnum.isCanRetry());
        return attrs;
    }
}
