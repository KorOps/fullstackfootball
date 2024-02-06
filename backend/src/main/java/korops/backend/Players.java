package korops.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("Players")
public class Players {private String id;
                      private String firstname;
                      private String lastname;
                      private String nationality;
                      private int age;
                      private String height;
                      private String foot;
                      private String position;
                      private String team;

}
