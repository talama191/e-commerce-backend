package base.repository;

import base.model.entity.Comment;
import base.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByProduct(Product product);
}
