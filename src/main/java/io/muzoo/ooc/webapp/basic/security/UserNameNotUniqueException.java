package io.muzoo.ooc.webapp.basic.security;

public class UserNameNotUniqueException extends UserServiceException {

    public UserNameNotUniqueException(String message) {
        super(message);
    }
}
