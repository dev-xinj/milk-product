package vn.shortsoft.products.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
    private Long id;
    private String userName;
    private String fullName;
    private String firstName;


}
