package jgorny.portal.configuration;

import jgorny.portal.auction.repository.model.Auction;
import jgorny.portal.auction.service.AuctionService;
import jgorny.portal.branch.repository.model.Branch;
import jgorny.portal.branch.service.BranchService;
import jgorny.portal.category.repository.model.Category;
import jgorny.portal.category.service.CategoryService;
import jgorny.portal.user.repository.model.User;
import jgorny.portal.user.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;

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
            User jax = User.builder()
                    .login("Jax")
                    .password("$2a$10$hJMqlcX6yldSFQFL4s4HkeALoIK9JbWbW6F.zBEkCJo4NR.ar8JLS")
                    .build();
            User yuri = User.builder()
                    .login("Yuri")
                    .password("$2y$10$xl6SDXEYyV10mQuubEXJteO7vKn9Xv1qTRLvuO9BTGIgC33hrqERG")
                    .build();
            User mate = User.builder()
                    .login("Mate")
                    .password("$2y$10$xl6SDXEYyV10mQuubEXJteO7vKn9Xv1qTRLvuO9BTGIgC33hrqERG")
                    .build();

            userService.createUser(jax);
            userService.createUser(yuri);
            userService.createUser(mate);

//            Roles ?
//            Sha256Utility.hash("admin")
//            Sha256Utility.hash("user")

        }
        if (branchService.findAllBranches().isEmpty()) {
            Branch meat = Branch.builder()
                    .id((long) 1)
                    .name("meat")
                    .build();
            Branch vegetables = Branch.builder()
                    .id((long) 2)
                    .name("vegetables ")
                    .build();
            Branch toys = Branch.builder()
                    .id((long) 3)
                    .name("toys")
                    .build();

            branchService.createBranch(meat);
            branchService.createBranch(vegetables);
            branchService.createBranch(toys);

//            id ?? capture of?
        }
        if (categoryService.findAllCategories().isEmpty()) {
            Category car = Category.builder()
                    .id((long) 1)
                    .name("car")
                    .branch(3)
                    .build();
            Category game = Category.builder()
                    .id((long) 2)
                    .name("game")
                    .branch(3)
                    .build();
            Category lego = Category.builder()
                    .id((long) 3)
                    .name("lego")
                    .branch(3)
                    .build();

            categoryService.createCategory(car);
            categoryService.createCategory(game);
            categoryService.createCategory(lego);
//            branch ???
        }
        if (auctionService.findAllAuctions().isEmpty()) {
            Auction zabawka = Auction.builder()
                    .id((long) 1)
                    .name("zabawka")
                    .price((double) 20)
                    .quantity(4)
                    .category()
                    .build();
            Auction koc = Auction.builder()
                    .id((long) 2)
                    .name("koc")
                    .price((double) 100)
                    .quantity(7)
                    .category()
                    .build();
            Auction kubek = Auction.builder()
                    .id((long) 3)
                    .name("kubek")
                    .price((double) 15)
                    .quantity(8)
                    .category()
                    .build();

            auctionService.createAuction(zabawka);
            auctionService.createAuction(koc);
            auctionService.createAuction(kubek);

        }

    }


}


    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }

    }
