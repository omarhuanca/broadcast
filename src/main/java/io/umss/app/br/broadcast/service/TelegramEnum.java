package io.umss.app.br.broadcast.service;

/**
 * TelegramEnum
 * 
 * @author Omar Huanca
 * @since 1.0
 */
public enum TelegramEnum {

    COMMAND_CAREER_ADVICE("/consejocarrera", "1"), COMMAND_FACULTATIVE_COUNCIL("/consejofacultativo", "2"),
    COMMAND_UNIVERSITY_COUNCIL("/consejouniversitario", "3"), COMMAND_CAREER_DIRECTOR("/directorcarrera", "4"),
    COMMAND_EXCLUSIVE_TEACHER("/docenteexclusivo", "5"), COMMAND_ACADEMIC_COORDINATOR("/coordinadoracademico", "6");

    private String code;
    private String value;

    TelegramEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}