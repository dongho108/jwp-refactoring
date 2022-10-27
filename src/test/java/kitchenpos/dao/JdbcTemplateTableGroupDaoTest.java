package kitchenpos.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import kitchenpos.domain.TableGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JdbcTemplateTableGroupDaoTest extends JdbcTemplateTest{

    private TableGroupDao tableGroupDao;

    @BeforeEach
    void setUp() {
        tableGroupDao = new JdbcTemplateTableGroupDao(dataSource);
    }

    @Test
    @DisplayName("데이터 베이스에 저장할 경우 id 값을 가진 엔티티로 반환한다.")
    void save() {
        final TableGroup tableGroup = tableGroupDao.save(getTableGroup());
        assertThat(tableGroup.getId()).isNotNull();
    }

    @Test
    @DisplayName("목록을 조회한다.")
    void list() {
        // given
        tableGroupDao.save(getTableGroup());

        // when
        final List<TableGroup> actual = tableGroupDao.findAll();

        // then
        assertThat(actual).hasSize(1);
    }
}
