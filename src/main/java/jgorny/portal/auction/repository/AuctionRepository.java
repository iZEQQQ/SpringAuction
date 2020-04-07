package jgorny.portal.auction.repository;

import jgorny.portal.auction.repository.model.Auction;
import jgorny.portal.category.repository.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {


    @Query("select a.id from Auction a")
    List<Long> findId();

    @Query("select a.id from Auction a where a.category = :category")
    List<Long> findId(Category category);

}




