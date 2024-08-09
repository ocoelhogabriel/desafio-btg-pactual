package com.gctech.btgpactual.ordems.controller;

import com.gctech.btgpactual.ordems.controller.dto.ApiResponse;
import com.gctech.btgpactual.ordems.controller.dto.OrderResponse;
import com.gctech.btgpactual.ordems.controller.dto.PaginationResponse;
import com.gctech.btgpactual.ordems.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "0") Integer pageSize,
            @PathVariable Long customerId) {

        var body = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));
        var totalOnOrders = orderService.findTotalOnOrderByCustomerId(customerId);
        return ResponseEntity.ok(new ApiResponse<>(
                Map.of("totalOnOrders", totalOnOrders),
                body.getContent(),
                PaginationResponse.fromPage(body)
        ));
    }
}
