package com.example.ecommercebe.repository;

import com.example.ecommercebe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
    @Query("select c from User c where c.userName = :name")
    Collection<User> findByName(String name);

    @Query(value = "select * from User u where u.userName = 1?", nativeQuery = true)
    Collection<User> findByNameNative(String name);

//    @Query("select new UserDTO (c.id, ) from User c INNER JOIN ShoppingCart sc ON c.id = sc.user.id")
//    Collection<UserDTO> getUserAndCart();

//    @Query("select c.userName as name , p.total as phoneNumber from User c INNER JOIN ShoppingCart p ON c.id = p.user.id")
//    List<UserProjection> getUserAndCartProjection();
//
//    @Query(value = "SELECT new ResultDTO(c.id, o.id, p.id, c.userName, c.email, o.createdAt, p.product.name, BigDecimal(p.product.price)) "
//            + " from User c, Order o ,OrderDetail p "
//            + " where c.id=o.user.id "
//            + " and o.id=p.order.id "
//            + " and c.id=?1 ")
//    List<ResultDTO> findResultDTOByCustomer(int id);
}
