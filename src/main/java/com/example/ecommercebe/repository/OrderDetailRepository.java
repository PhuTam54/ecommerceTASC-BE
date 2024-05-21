package com.example.ecommercebe.repository;

import com.example.ecommercebe.entity.OrderDetail;
import com.example.ecommercebe.entity.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {

}
