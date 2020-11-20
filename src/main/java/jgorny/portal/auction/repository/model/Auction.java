package jgorny.portal.auction.repository.model;

import jgorny.portal.category.repository.model.Category;
import jgorny.portal.order.repository.model.OrderItem;
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
@Table(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    public Auction(String name, Double price, Integer quantity, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    @OneToMany(mappedBy = "auction", fetch = FetchType.LAZY)
    private List<OrderItem> items;

}
