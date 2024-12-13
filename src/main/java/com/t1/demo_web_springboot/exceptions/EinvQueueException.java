package com.t1.demo_web_springboot.exceptions;

public class EinvQueueException extends SystemException {

    /**
     * @param message
     */
    public EinvQueueException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public EinvQueueException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public EinvQueueException(String message, Throwable cause) {
        super(message, cause);
    }
}