package POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("id")
    private String user_id; // by choise i want to name is user_id
    @JsonProperty("name")
    private String user_name;

    public User(String user_id, String user_name) {
        this.user_id = user_id;
        this.user_name = user_name;
    }

    public User() { }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", user_name=" + user_name +
                '}';
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
