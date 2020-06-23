package io.umss.app.br.broadcast.dto;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * SubscriberCellphone
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Documented
@Constraint(validatedBy = SubscriberCellphoneValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SubscriberCellphone {

    String message() default "The correct cellphone format is +591######## (11 digits)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean canBeNull() default false;
}
