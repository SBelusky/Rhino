package com.samuell.rhino.controller.form_validation;

import java.util.regex.Pattern;

public class ValidationHelpers {
    //email pattern, vzu6itie:
    /*Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
    return matcher.find();*/
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    public static final Pattern NOT_BLANK_SPACES = Pattern.compile(".*\\S.*");


}
