package edu.finalproject.webservice.api.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

/**
 * Class that represents a user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement
@Getter
@Setter
public class User {

    // ATTRIBUTES
    private int id;
    private String username;
    private String password;
    private String mail;
}