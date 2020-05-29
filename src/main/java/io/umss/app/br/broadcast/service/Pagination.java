package io.umss.app.br.broadcast.service;

/**
 * Pagination
 * 
 * @author Omar Huanca
 * @since 1.0
 */
public enum Pagination {

    DEFAULT_PAGESIZE(10), DEFAULT_PAGE(0), MAX_PAGE_SIZE(1000);

    private Integer code;

    Pagination(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}