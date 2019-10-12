package rs.rnk.tasks.rest.model;

import java.util.Base64;

public class LoginInfo {

    private String username;
    private String password;

    private LoginInfo() {
    }

    private LoginInfo(String authHeader) {
        String credentialsEncoded = authHeader.substring(authHeader.indexOf(" ") + 1);
        String credentialsDecoded = new String(Base64.getDecoder().decode(credentialsEncoded));
        int colonIndex = credentialsDecoded.indexOf(":");
        this.username = credentialsDecoded.substring(0, colonIndex);
        this.password = credentialsDecoded.substring(colonIndex + 1);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder {

        private String authHeader;

        public Builder(String authHeader) {
            this.authHeader = authHeader;
        }

        public LoginInfo build() {
            if(authHeader == null)
                return null;
            return new LoginInfo(this.authHeader);
        }
    }
}
