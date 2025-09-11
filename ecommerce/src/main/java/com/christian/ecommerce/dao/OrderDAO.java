package com.christian.ecommerce.dao;

import com.christian.ecommerce.dto.MonthlyRevenue;
import com.christian.ecommerce.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDAO extends CrudRepository<Order, Integer> {
    List<Order> findAllByStatus(Integer status);

    //custom query to retrieve revenue
    @Query("SELECT new "
    + " com.christian.ecommerce.dto.MonthlyRevenue(month(o.date), sum(o.totalValue)) "
    + " FROM Order o "
    + " WHERE year(o.date) = :year "
    + " GROUP BY month(o.date) ")
    public List<MonthlyRevenue> getRevenue(@Param("year") Integer year);
}
