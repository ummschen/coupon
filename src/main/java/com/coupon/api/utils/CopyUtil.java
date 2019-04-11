package com.coupon.api.utils;

import com.coupon.api.exception.ServiceException;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author fuda
 * @since 2018/7/20
 */
public class CopyUtil {

	public static <T> T copy(Object source, Class<T> targetClazz) {
		T target;
		try {
			target = targetClazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ServiceException(e);
		}
		BeanUtils.copyProperties(source, target);
		return target;
	}

	public static <T> List<T> copy(List sourceList, Class<T> targetClazz) {
		List<T> targetList = Lists.newArrayList();
		T targetObject;
		for (Object item : sourceList) {
			try {
				targetObject = targetClazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new ServiceException(e);
			}
			BeanUtils.copyProperties(item, targetObject);
			targetList.add(targetObject);
		}
		return targetList;
	}
}
