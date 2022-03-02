package base.controller;

import base.model.entity.Comment;
import base.model.entity.form.CommentForm;
import base.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    
    @GetMapping("/product")
    public ResponseEntity<List<Comment>> loaddComment(@RequestParam int productId){
        return ResponseEntity.ok().body(commentService.findByProduct(productId));
    }

    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(@RequestBody CommentForm commentForm){
        return ResponseEntity.ok().body(commentService.addComment(commentForm));
    }
}
