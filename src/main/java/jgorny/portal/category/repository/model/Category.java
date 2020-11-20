package jgorny.portal.category.repository.model;

import jgorny.portal.auction.repository.model.Auction;
import jgorny.portal.branch.repository.model.Branch;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "branch")
    private Branch branch;

    public Category(String name, Branch branch) {
        this.name = name;
        this.branch = branch;
    }

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Auction> auctions;
}
