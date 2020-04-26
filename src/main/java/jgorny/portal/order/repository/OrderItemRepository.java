package jgorny.portal.order.repository;

import jgorny.portal.order.repository.model.Order;
import jgorny.portal.order.repository.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {


    @Query("select oi.id from OrderItem oi")
    List<Long> findId();

    @Query("select oi.id from OrderItem oi where oi.order = :order")
    List<Long> findId(Order order);


}

