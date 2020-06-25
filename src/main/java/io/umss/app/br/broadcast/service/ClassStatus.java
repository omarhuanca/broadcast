package io.umss.app.br.broadcast.service;

/**
 * ClassStatus
 * 
 * @author Omar Huanca
 * @since 1.0
 */
public enum ClassStatus {

    ENABLE(1),
    DISABLE(0);

    private Integer code;

    ClassStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
