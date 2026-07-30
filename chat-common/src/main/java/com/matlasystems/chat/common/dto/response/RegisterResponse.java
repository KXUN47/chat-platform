package com.matlasystems.chat.common.dto.response;

import com.matlasystems.chat.common.dto.user.UserDTO;
import java.io.Serializable;

/** Successful account-registration response payload. */
public final class RegisterResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private UserDTO user;
    public RegisterResponse() { }
    public RegisterResponse(UserDTO user) { this.user = user; }
    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }
}
