package se.yrgo.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.domain.ReviewLikes;
import se.yrgo.service.reviewLikes.ReviewLikesService;

@RestController
@RequestMapping("likes")
public class ReviewLikesRestController {
    private ReviewLikesService rls;

    public ReviewLikesRestController(ReviewLikesService rls) {
        this.rls = rls;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addLike(@RequestBody ReviewLikes newLike) {
        rls.addLike(newLike);
        return ResponseEntity.ok("Like added");
    }

}
