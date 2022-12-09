package com.myshop.order.command.application;

import com.myshop.common.VersionConflictException;
import com.myshop.order.NoOrderException;
import com.myshop.order.command.domain.Order;
import com.myshop.order.command.domain.OrderNo;
import com.myshop.order.command.domain.OrderRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class StartShippingService {
  private OrderRepository orderRepository;

  public StartShippingService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Transactional
  public void startShipping(StartShippingRequest req) {
    Optional<Order> orderOpt = orderRepository.findById(new OrderNo(req.getOrderNumber()));
    Order order = orderOpt.orElseThrow(() -> new NoOrderException());

    // TODO: 관리자가 배송 처리할 때 오류남. , 버전이 같음.
    LOGGER.info("### order {}", order.getVersion());
    LOGGER.info("### req order {}", req.getVersion());
    if (order.matchVersion(req.getVersion())) {
      throw new VersionConflictException();
    }
    order.startShipping();
  }
}
