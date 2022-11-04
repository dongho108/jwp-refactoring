package kitchenpos.core.order.application.dto;

import java.util.List;

public class OrderRequest {

    private final Long orderTableId;
    private final String orderStatus;
    private final List<OrderLineItemRequest> orderLineItemRequests;

    public OrderRequest(final Long orderTableId, final String orderStatus,
                        final List<OrderLineItemRequest> orderLineItemRequests) {
        this.orderTableId = orderTableId;
        this.orderStatus = orderStatus;
        this.orderLineItemRequests = orderLineItemRequests;
    }

    public Long getOrderTableId() {
        return orderTableId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public List<OrderLineItemRequest> getOrderLineItemRequests() {
        return orderLineItemRequests;
    }
}