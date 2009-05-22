package org.richfaces.component.html;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.richfaces.validator.FacesBeanValidator;

public class HtmlInputSecret extends javax.faces.component.html.HtmlInputSecret {
    
    @Override
    protected void validateValue(FacesContext context, Object newValue) {
        // If our value is valid, enforce the required property if present
        if (isValid() && isRequired() && isEmpty(newValue)) {
            super.validateValue(context, newValue);
        }
        // If our value is valid and not empty, call all validators
        if (isValid()) {
            Validator[] validators = this.getValidators();
            if (validators != null) {
                for (Validator validator : validators) {
                    try {
                        if (validator instanceof FacesBeanValidator
                                || !isEmpty(newValue)) {
                            validator.validate(context, this, newValue);
                        }
                    } catch (ValidatorException ve) {
                        // If the validator throws an exception, we're
                        // invalid, and we need to add a message
                        setValid(false);
                        FacesMessage message;
                        String validatorMessageString = getValidatorMessage();

                        if (null != validatorMessageString) {
                            message = new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR,
                                    validatorMessageString,
                                    validatorMessageString);
                            message.setSeverity(FacesMessage.SEVERITY_ERROR);
                        } else {
                            message = ve.getFacesMessage();
                        }
                        if (message != null) {
                            context.addMessage(getClientId(context), message);
                        }
                    }
                }
            }
        }
    }

    private static boolean isEmpty(Object value) {

        if (value == null) {
            return true;
        } else if ((value instanceof String) && (((String) value).length() < 1)) {
            return true;
        } else if (value.getClass().isArray()) {
            if (0 == java.lang.reflect.Array.getLength(value)) {
                return true;
            }
        } else if (value instanceof List) {
            if (((List<?>) value).isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
