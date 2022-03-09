package base.service;

import base.exception.ResourceNotFoundException;
import base.model.composite.CommentId;
import base.model.entity.Comment;
import base.model.entity.Product;
import base.model.entity.User;
import base.model.entity.form.CommentForm;
import base.repository.CommentRepository;
import base.repository.ProductRepository;
import base.repository.UserRepository;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Comment> findByProduct(int productId){
        Product product =  productRepository.findById(productId);
        return commentRepository.findByProduct(product);
    }

    public Comment addComment(CommentForm commentForm){
        User user =userRepository.findById(commentForm.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User", "id", commentForm.getUserId()));;
        Product product = productRepository.findById(commentForm.getProductId());
        Comment comment = new Comment();
        CommentId commentId = new CommentId(commentForm.getProductId(), commentForm.getUserId());
        comment.setId(commentId);
        comment.setContent(commentForm.getComment());
        comment.setUser(user);
        comment.setProduct(product);
       comment.setRate(commentForm.getRate());
        return commentRepository.save(comment);
    }
}
