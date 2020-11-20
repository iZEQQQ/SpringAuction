package jgorny.portal.configuration;

import jgorny.portal.auction.repository.model.Auction;
import jgorny.portal.auction.service.AuctionService;
import jgorny.portal.branch.repository.model.Branch;
import jgorny.portal.branch.service.BranchService;
import jgorny.portal.category.repository.model.Category;
import jgorny.portal.category.service.CategoryService;
import jgorny.portal.user.repository.model.User;
import jgorny.portal.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InitializedData {

    private final UserService userService;

    private final BranchService branchService;

    private final CategoryService categoryService;

    private final AuctionService auctionService;

    @Autowired
    public InitializedData(UserService userService, BranchService branchService,
                           CategoryService categoryService, AuctionService auctionService) {

        this.userService = userService;
        this.branchService = branchService;
        this.categoryService = categoryService;
        this.auctionService = auctionService;
    }


    @PostConstruct
    private synchronized void init() {
        if (userService.findUser("Jax").isEmpty()) {
            BCryptPasswordEncoder bc = new BCryptPasswordEncoder();

            User jax = User.builder()
                    .login("Jax")
                    .password(bc.encode("admin"))
                    .roles(List.of("Admin", "User"))
                    .build();
            User yuri = User.builder()
                    .login("Yuri")
                    .password(bc.encode("user"))
                    .roles(List.of("User"))
                    .build();
            User mate = User.builder()
                    .login("Mate")
                    .password(bc.encode("user"))
                    .roles(List.of("User"))
                    .build();

            userService.createUser(jax);
            userService.createUser(yuri);
            userService.createUser(mate);


            Branch meat = Branch.builder()
                    .name("meat")
                    .build();
            Branch vegetables = Branch.builder()
                    .name("vegetables ")
                    .build();
            Branch toys = Branch.builder()
                    .name("toys")
                    .build();

            branchService.createBranch(meat);
            branchService.createBranch(vegetables);
            branchService.createBranch(toys);


            Category car = Category.builder()
                    .name("car")
                    .branch(toys)
                    .build();
            Category game = Category.builder()
                    .name("game")
                    .branch(toys)
                    .build();
            Category lego = Category.builder()
                    .name("lego")
                    .branch(toys)
                    .build();

            categoryService.createCategory(car);
            categoryService.createCategory(game);
            categoryService.createCategory(lego);

            Auction zabawka = Auction.builder()
                    .name("zabawka")
                    .price((double) 20)
                    .quantity(4)
                    .category(game)
                    .build();
            Auction koc = Auction.builder()
                    .name("koc")
                    .price((double) 100)
                    .quantity(7)
                    .category(game)
                    .build();
            Auction kubek = Auction.builder()
                    .name("kubek")
                    .price((double) 15)
                    .quantity(8)
                    .category(lego)
                    .build();

            auctionService.createAuction(zabawka);
            auctionService.createAuction(koc);
            auctionService.createAuction(kubek);


        }
    }
}