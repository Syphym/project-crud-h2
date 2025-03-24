package ph.syphym.evergreen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ph.syphym.evergreen.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}
