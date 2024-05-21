package com.example.ecommercebe.repository;

import com.example.ecommercebe.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAll(Pageable pageable);
    @Query("SELECT u FROM Product u WHERE u.name = :name")
    Collection<Product> findAllActiveProducts(Sort sort, String name);

    @Query(
            value = "SELECT * FROM PRODUCTS u WHERE u.status = ?1",
            nativeQuery = true)
    Collection<Product> findAllActiveProductsNative(int status);

//    @Query("select new UserDTO (c.userName, BigDecimal(sc.total)) from User c INNER JOIN ShoppingCart sc ON c.id = sc.user.id")
//    List<UserDTO> getUserAndCart();
//
//    @Query(value = "SELECT new ResultDTO(c.id, o.id, p.id, c.userName, c.email, o.createdAt, p.product.name, Double(p.product.price)) "
//            + " from User c, Order o ,OrderDetail p "
//            + " where c.id=o.user.id "
//            + " and o.id=p.order.id "
//            + " and c.id=?1 ")
//    Collection<ResultDTO> findResultDTOByCustomer(int id);
}
