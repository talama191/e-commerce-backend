package base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import base.model.entity.Category;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Category findByName(String name);
}
