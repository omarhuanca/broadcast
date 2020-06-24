package io.umss.app.br.broadcast.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

/**
 * SubscriberCellphoneValidator
 * 
 * @author Omar Huanca
 * @since 1.0
 */
public class SubscriberCellphoneValidator implements ConstraintValidator<SubscriberCellphone, String> {

    private final String SUBSCRIBER_CELLPHONE_REGEX = "^\\+[5]{1}[9]{1}[1]{1}[0-9]{8}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Boolean response = false;

        if (null == value) {
            return false;
        }

        if (StringUtils.isBlank(value)) {
            return false;
        }

        response = value.matches(SUBSCRIBER_CELLPHONE_REGEX);
        return response;
    }
}
