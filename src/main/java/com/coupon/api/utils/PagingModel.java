package com.coupon.api.utils;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 功能描述:
 *
 * @Author: wangyanjing
 * @Date: 2019/1/25 14:01
 **/
@Data
public class PagingModel<T> {

	private int pageIndex = SystemConstants.DEFAULT_PAGE_INDEX;

	private int pageSize = SystemConstants.DEFAULT_PAGE_SIZE;

	private long total;

	private List<T> results;

	public <R> PagingModel<R> convert(Function<? super T, ? extends R> mapper) {
		PagingModel<R> ret = new PagingModel<>();
		ret.setPageIndex(this.getPageIndex());
		ret.setPageSize(this.getPageSize());
		ret.setTotal(this.getTotal());
		if (!CollectionUtils.isEmpty(this.getResults())) {
			ret.setResults(this.getResults().stream()
					.map(mapper)
					.filter(o -> o != null)
					.collect(Collectors.toList())
			);
		}
		return ret;
	}

	public List<T> getResults() {
		if (results == null) {
			return Collections.emptyList();
		} else {
			return results;
		}
	}

}
