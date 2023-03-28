package cn.qingyumao.doc.book.domain;

import cn.hutool.core.collection.CollUtil;
import cn.qingyumao.scaffold.common.exception.DataNonExistentException;
import cn.qingyumao.scaffold.ddd.domain.IDGeneratorHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 书籍目录
 */
public class Catalog {

    private final CatalogId id;

    private CatalogName name;

    private SortValue sortValue;


    /**
     * 是否是根目录
     */
    private final Boolean isRoot;
    /**
     * 孩子节点
     */

    private List<Catalog> children;
    /**
     * 孩子节点数量
     */
    private int childrenQty;

    public Catalog(CatalogId id, CatalogName name, SortValue sortValue, List<Catalog> children) {
        this.id = id;
        this.name = name;
        this.sortValue = sortValue;
        this.isRoot = false;
        this.children = children;
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        assert children != null;
        this.childrenQty = children.stream().mapToInt(c -> c.childrenQty).sum();
    }

    public Catalog(CatalogName name, SortValue sortValue) {
        this.id = IDGeneratorHolder.getIdGenerator(CatalogId.class).generate();
        this.name = name;
        this.sortValue = sortValue;
        this.isRoot = false;
        this.children = new ArrayList<>();
        this.childrenQty = 0;
    }

    /**
     * 新增目录
     *
     * @param catalogName
     * @param parentId
     */
    public void addCatalog(CatalogName catalogName, CatalogId parentId) {
        if (parentId == null) {
            this.children.add(new Catalog(catalogName, this.sortValue.getNext()));
        } else {
            final Optional<Catalog> parentCatalog = findById(parentId);
            if (parentCatalog.isEmpty()) {
                throw new DataNonExistentException("创建目录失败：新增{}非法父节点", catalogName.getVal());
            }
            parentCatalog.get().addCatalog(catalogName, null);
        }
    }

    public Optional<Catalog> findById(CatalogId id) {
        if (CollUtil.isNotEmpty(this.children)){
            for (Catalog child : this.children) {
                if (child.id.equals(id)){
                    return Optional.of(child);
                }
            }
        }
        return Optional.empty();
    }
}
