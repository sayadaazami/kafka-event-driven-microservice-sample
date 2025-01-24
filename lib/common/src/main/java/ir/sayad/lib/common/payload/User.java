package ir.sayad.lib.common.payload;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private String email;
    private Integer age;
}
