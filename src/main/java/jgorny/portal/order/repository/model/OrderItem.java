package jgorny.portal.order.repository.model;

import jgorny.portal.auction.repository.model.Auction;
import lombok.*;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    private Order order;

    @ManyToOne()
    private Auction auction;

    private int quantity;

}
