package entity.pattern1;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Teacher1 {

    @Id
    private Long id;

    private String name;
}
