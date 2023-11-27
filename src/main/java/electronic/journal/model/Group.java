package electronic.journal.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "study_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "group_subject", // Назва таблиці, яка буде зберігати зв'язки
            joinColumns = {@JoinColumn(name = "group_id")}, // Стовпець у цій таблиці, що зберігає ID групи
            inverseJoinColumns = {@JoinColumn(name = "subject_id")} // Стовпець, що зберігає ID предмета
    )
    private List<Subject> subjects;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
