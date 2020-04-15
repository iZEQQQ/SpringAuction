package jgorny.portal.order.repository.model;

import jgorny.portal.user.repository.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

    public Order(LocalDateTime localDateTime, User user) {
        this.localDateTime = localDateTime;
        this.user = user;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<OrderItem> items;

}
