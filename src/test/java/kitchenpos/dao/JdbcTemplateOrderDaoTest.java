package kitchenpos.dao;

import static kitchenpos.domain.OrderStatus.COMPLETION;
import static kitchenpos.domain.OrderStatus.COOKING;
import static kitchenpos.domain.OrderStatus.MEAL;
import static kitchenpos.fixture.OrderFixture.getOrderRequest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import kitchenpos.dao.order.JdbcTemplateOrderDao;
import kitchenpos.dao.order.OrderDao;
import kitchenpos.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class JdbcTemplateOrderDaoTest extends JdbcTemplateTest {

    private OrderDao orderDao;

    @BeforeEach
    void setUp() {
        orderDao = new JdbcTemplateOrderDao(dataSource);
    }

    @Test
    @DisplayName("데이터 베이스에 저장할 경우 id 값을 가진 엔티티로 반환한다.")
    void save() {
        final Order savedOrder = orderDao.save(getOrderRequest());
        assertThat(savedOrder.getId()).isNotNull();
    }

    @ParameterizedTest(name = "해당 주문의 주문테이블 상태 {0} 가 COOKING, MEAL에 속하면 true 를 반환한다.")
    @ValueSource(strings = {"COOKING", "MEAL"})
    void existsByOrderTableIdInAndOrderStatusIn(final String orderStatus) {
        // given
        final List<String> orderStatuses = Arrays.asList(COOKING.name(), MEAL.name());
        final Order savedOrder = orderDao.save(getOrderRequest(orderStatus));

        // when
        final boolean actual = orderDao.existsByOrderTableIdInAndOrderStatusIn(Arrays.asList(savedOrder.getOrderTableId()),
                orderStatuses);

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("해당 주문의 주문테이블 상태가 {COOKING, MEAL} 에 속하지 않으면 true 를 반환한다.")
    void existsByOrderTableIdInAndInvalidOrderStatusIn() {
        // given
        final List<String> orderStatuses = Arrays.asList(COOKING.name(), MEAL.name());
        final Order savedOrder = orderDao.save(getOrderRequest(COMPLETION.name()));

        // when
        final boolean actual = orderDao.existsByOrderTableIdInAndOrderStatusIn(Arrays.asList(savedOrder.getOrderTableId()),
                orderStatuses);

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("목록을 조회한다.")
    void list() {
        // given
        orderDao.save(getOrderRequest());

        // when
        final List<Order> actual = orderDao.findAll();

        // then
        assertThat(actual).hasSize(1);
    }
}
