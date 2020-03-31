package jgorny.portal.auction.serviece;

import jgorny.portal.auction.repository.AuctionRepository;
import jgorny.portal.auction.serviece.model.Auction;
import jgorny.portal.branch.serviece.model.Branch;
import jgorny.portal.category.serviece.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionServiece {

    private AuctionRepository repository;

    @Autowired
    public AuctionServiece(AuctionRepository repository) {
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
