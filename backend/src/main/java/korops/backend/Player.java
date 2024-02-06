package korops.backend;

import lombok.NoArgsConstructor;


public record Player(String id,
                     String firstname,
                     String lastname,
                     String nationality,
                     int age,
                     int height,
                     String foot,
                     String position,
                     String team) {

}
