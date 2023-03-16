package cn.qingyumao.doc.book.domain;

/**
 * 排序值
 */
public class SortValue {
    public int MAX_VALUE = 100;
    private final int val;


    public SortValue(int val) {
        if (val < 0) {
            throw new IllegalStateException("排序值不能小于0");
        }
        if (val > MAX_VALUE) {
            throw new IllegalStateException("同一级别目录最多只能新建：" + MAX_VALUE);
        }
        this.val = val;
    }

    public SortValue getNext() {
        return new SortValue(val + 1);
    }
}
