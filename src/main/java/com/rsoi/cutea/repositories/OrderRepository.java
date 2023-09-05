package com.rsoi.cutea.repositories;

import com.rsoi.cutea.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}