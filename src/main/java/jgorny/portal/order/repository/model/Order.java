package jgorny.portal.order.repository.model;

import jgorny.portal.auction.repository.model.Auction;
import jgorny.portal.user.repository.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Long id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "users")
    private User user;

    public Order(Date date, User user) {
        this.date = date;
        this.user = user;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<User> users;


}
