package com.myshop.order.query.dao;

import com.myshop.order.query.dto.OrderSummary;
import com.myshop.order.query.dto.OrderSummary_;
import java.time.LocalDateTime;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class OrderSummarySpecs {
  public static Specification<OrderSummary> ordererId(String ordererId) {
    return (Root<OrderSummary> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
        cb.equal(root.<String>get("ordererId"), ordererId);
  }

  public static Specification<OrderSummary> orderDateBetween(LocalDateTime from, LocalDateTime to) {
    return (Root<OrderSummary> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
        cb.between(root.get(OrderSummary_.orderDate), from, to);
  }
}
