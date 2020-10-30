package jgorny.portal.user.repository.model;

import jgorny.portal.order.repository.model.Order;
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
@Table(name = "users")
public class User {

    @Id
    private String login;

    private String password;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Order> orders;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_name"))
    @Column(name = "role_name")
    private List<String> roles;

    public User(String login) {
        this.login = login;
    }
}
