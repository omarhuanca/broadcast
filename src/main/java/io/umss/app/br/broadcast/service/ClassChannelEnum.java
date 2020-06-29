package io.umss.app.br.broadcast.service;

/**
 * ClassChannelEnum
 * 
 * @author Omar Huanca
 * @since 1.0
 */
public enum ClassChannelEnum {

    TELEGRAM("telegram"), SMS("sms"), EMAIL("email");

    public String code;

    ClassChannelEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}