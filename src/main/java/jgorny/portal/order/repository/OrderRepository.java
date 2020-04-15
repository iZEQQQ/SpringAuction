package jgorny.portal.order.repository;

import jgorny.portal.order.repository.model.Order;
import jgorny.portal.user.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<Order,Long> {

    @Query("select o.id from Orders o")
    List<Long> findId();

    @Query("select o.id from Orders o where o.user = :user")
    List<Long> findId(User user);
}
