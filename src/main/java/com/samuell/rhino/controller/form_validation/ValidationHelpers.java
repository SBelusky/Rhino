package com.samuell.rhino.controller.form_validation;

import java.util.regex.Pattern;

public class ValidationHelpers {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    public static final Pattern NOT_BLANK_SPACES = Pattern.compile(".*\\S.*");

    public static final Pattern HEX_COLOR = Pattern.compile("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$");
}
