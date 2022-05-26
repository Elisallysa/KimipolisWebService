package edu.finalproject.webservice.api.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement
@Getter
@Setter
public class User {
    private int id;
    private String username;
    private String password;
    private String mail;
}