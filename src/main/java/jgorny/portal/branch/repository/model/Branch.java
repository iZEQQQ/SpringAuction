package jgorny.portal.branch.repository.model;

import jgorny.portal.category.repository.model.Category;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Branch(String name) {
        this.name=name;
    }

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Category> categories;

}
