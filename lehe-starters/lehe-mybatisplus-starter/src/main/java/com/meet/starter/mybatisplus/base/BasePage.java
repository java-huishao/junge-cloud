package com.lehe.starter.mybatisplus.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.Data;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Data
@ToString
public class BasePage<T extends Object> implements IPage<T> {

    private static final long serialVersionUID = 8235996863226528798L;
    private List<T> records;
    private long total;
    private long size;
    private long current;
    private String[] ascs;
    private String[] descs;
    private boolean optimizeCountSql;
    private boolean isSearchCount;

    private String searchKey;
    private String startTime;
    private String endTime;

    private String sort;
    private String order;
    private BaseParam param;

    public BasePage() {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        this.optimizeCountSql = true;
        this.isSearchCount = true;
    }

    public BasePage(long current, long size, BaseParam param) {
        this(current, size, 0L);
        this.param = param;
    }

    public BasePage(long current, long size, long total) {
        this(current, size, total, true);
    }

    public BasePage(long current, long size, long total, List<T> list) {
        this(current, size, total, true);
        this.records = list;
    }

    public BasePage(long current, long size, boolean isSearchCount) {
        this(current, size, 0L, isSearchCount);
    }

    public BasePage(long current, long size, long total, boolean isSearchCount) {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        this.optimizeCountSql = true;
        this.isSearchCount = true;
        if (current > 1L) {
            this.current = current;
        }

        this.size = size;
        this.total = total;
        this.isSearchCount = isSearchCount;
    }

    public boolean hasPrevious() {
        return this.current > 1L;
    }

    public boolean hasNext() {
        return this.current < this.getPages();
    }

    @Override
    public List<T> getRecords() {
        return this.records;
    }

    @Override
    public BasePage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public BasePage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public BasePage<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return this.current;
    }

    @Override
    public BasePage<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    public BasePage<T> setAscs(List<String> ascs) {
        if (CollectionUtils.isNotEmpty(ascs)) {
            this.ascs = ascs.toArray(new String[0]);
        }

        return this;
    }

    public BasePage<T> setAsc(String... ascs) {
        this.ascs = ascs;
        return this;
    }

    public BasePage<T> setDesc(String... descs) {
        this.descs = descs;
        return this;
    }

    @Override
    public boolean optimizeCountSql() {
        return this.optimizeCountSql;
    }

    public BasePage<T> setSearchCount(boolean isSearchCount) {
        this.isSearchCount = isSearchCount;
        return this;
    }

    public BasePage<T> setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
        return this;
    }

    @Override
    public List<OrderItem> orders() {
        return null;
    }
}
