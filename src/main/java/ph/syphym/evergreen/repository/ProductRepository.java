package ph.syphym.evergreen.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ph.syphym.evergreen.entity.Product;


import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE p.description LIKE  %:criteria% OR p.category.name LIKE %:criteria% OR p.name LIKE %:criteria% OR p.manufacturer LIKE %:criteria%")
    List<Product> findByCriteria(String criteria, Pageable pageable);

    List<Product> findAllByOrderByPriceDesc(Pageable pageable);

    List<Product> findAllByOrderByNameDesc(Pageable pageable);

    List<Product> findAllByOrderByPriceAsc(Pageable pageable);

    List<Product> findAllByOrderByNameAsc(Pageable pageable);

}
