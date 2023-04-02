package cn.qingyumao.space.institution.adapter.web.req;

import cn.hutool.core.util.StrUtil;
import cn.qingyumao.space.institution.infrastructure.repository.dao.InstitutionDO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class InstitutionPageQry extends PageRequest {
    /**
     * 根据名称模糊查询
     */
    @Getter
    @Setter
    private String name;

    /**
     * Creates a new {@link PageRequest} with sort parameters applied.
     *
     * @param pageNumber zero-based page index, must not be negative.
     * @param pageSize the size of the page to be returned, must be greater than 0.
     */
    protected InstitutionPageQry(int pageNumber, int pageSize) {
        super(pageNumber, pageSize, Sort.unsorted());
    }


    public Specification<InstitutionDO> specificationFromAdmin() {
        return (root, query, cb) -> {
            if (StrUtil.isEmpty(name)) {
                return cb.isTrue(cb.literal(true));
            }
            return cb.like(root.get("name"), "%" + name + "%");
        };
    }
}
