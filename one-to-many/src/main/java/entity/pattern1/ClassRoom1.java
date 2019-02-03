package entity.pattern1;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class ClassRoom1 {

    @Id
    private Long id;

    private String name;

    @OneToOne
    private Teacher1 homeroomTeacher;

    @OneToMany
    private Set<Student1> students;

}
