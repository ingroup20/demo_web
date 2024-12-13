package com.t1.demo_web_springboot.exceptions;

public class SystemException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    //確保序列化和反序列化的版本兼容性。
    //防止 Java 的默認 serialVersionUID 計算導致意外的版本不匹配問題。
    //提高序列化類的穩定性，特別是在類結構可能變更（如添加非關鍵欄位）的情況下。

    public SystemException() {
    }

    /**
     * @param message
     *            錯誤訊息
     */
    public SystemException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public SystemException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     *            錯誤訊息
     * @param cause
     *            the cause (可使用 Throwable.getCause() method 取回)
     */
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }




}
