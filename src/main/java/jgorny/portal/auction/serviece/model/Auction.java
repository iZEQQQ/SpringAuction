package jgorny.portal.auction.serviece.model;

import jgorny.portal.category.serviece.model.Category;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    public Auction(String name, Double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

}
