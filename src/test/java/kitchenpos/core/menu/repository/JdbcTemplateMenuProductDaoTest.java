package kitchenpos.core.menu.repository;

import static kitchenpos.fixture.MenuFixture.getMenuProductRequest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import kitchenpos.core.menu.domain.MenuProduct;
import kitchenpos.core.menu.domain.MenuProductDao;
import kitchenpos.common.JdbcTemplateTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JdbcTemplateMenuProductDaoTest extends JdbcTemplateTest {

    private MenuProductDao menuProductDao;

    @BeforeEach
    void setUp() {
        menuProductDao = new JdbcTemplateMenuProductDao(dataSource);
    }

    @Test
    @DisplayName("데이터 베이스에 저장할 경우 id 값을 가진 엔티티로 반환한다.")
    void save() {
        final MenuProduct savedMenuProduct = menuProductDao.save(getMenuProductRequest(1L));
        assertThat(savedMenuProduct.getSeq()).isNotNull();
    }

    @Test
    @DisplayName("목록을 조회한다.")
    void list() {
        final List<MenuProduct> actual = menuProductDao.findAll();
        assertThat(actual).hasSize(6);
    }
}