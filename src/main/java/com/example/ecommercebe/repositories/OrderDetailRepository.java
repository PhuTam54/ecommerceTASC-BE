package com.example.ecommercebe.repositories;

import com.example.ecommercebe.entities.OrderDetail;
import com.example.ecommercebe.entities.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {

}
