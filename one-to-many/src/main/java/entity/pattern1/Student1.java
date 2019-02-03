package entity.pattern1;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Student1 {

    @Id
    private Long id;

    private String name;

}
