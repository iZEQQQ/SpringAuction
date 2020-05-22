package jgorny.portal.auction.service;

import jgorny.portal.auction.repository.AuctionRepository;
import jgorny.portal.auction.repository.model.Auction;
import jgorny.portal.category.repository.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {

    private AuctionRepository repository;

    @Autowired
    public AuctionService(AuctionRepository repository) {
        this.repository = repository;
    }

    public List<Long> findAllIds(Category category) {
        return repository.findId(category);
    }

    public Optional<Auction> findAuction(Long id) {
        return repository.findById(id);
    }

    public void createAuction(Auction auction) {
        repository.save(auction);
    }

    public void updateAuction(Auction auction) {
        repository.save(auction);
    }

    public void deleteAuction(Auction auction) {
        repository.delete(auction);
    }


}
