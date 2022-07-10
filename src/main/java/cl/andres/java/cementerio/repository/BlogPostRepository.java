package cl.andres.java.cementerio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.andres.java.cementerio.model.BlogPost;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

	@Query(value = "SELECT * FROM blog_post ORDER BY id DESC LIMIT 3", nativeQuery = true)
	List<BlogPost> findLastThree();
}
