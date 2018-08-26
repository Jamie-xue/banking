package cn.icbc.manager.error;

/**
 * @Auther: asus
 * @Date: 2018/8/25 15:08
 */
public enum ErrorEnum {
    ID_NULL("521","ID不能为空",false),
    UN_KNOWN("999","未知异常",false);

    private String code;
    private String message;
    private boolean canRetry;


    ErrorEnum(String code, String message, boolean canRetry) {
        this.code = code;
        this.message = message;
        this.canRetry = canRetry;
    }

    public static ErrorEnum getByCode(String code){
        for(ErrorEnum errorEnum:ErrorEnum.values()){

            if (code.equals(errorEnum.getCode())){
                return errorEnum;
            }
        }
        return UN_KNOWN;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isCanRetry() {
        return canRetry;
    }
}
