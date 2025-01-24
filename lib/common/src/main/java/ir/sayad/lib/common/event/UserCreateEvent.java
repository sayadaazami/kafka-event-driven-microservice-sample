package ir.sayad.lib.common.event;

import lombok.Data;

@Data
public class UserCreateEvent {
    private String id;
    private String name;
    private String email;
    private Integer age;
}
