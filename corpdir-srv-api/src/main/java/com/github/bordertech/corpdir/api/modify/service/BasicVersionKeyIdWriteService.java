package com.github.bordertech.corpdir.api.modify.service;

import com.github.bordertech.corpdir.api.common.ApiVersionable;

/**
 * Basic write service for keyed API object that have version data.
 *
 * @param <T> the keyed API object
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface BasicVersionKeyIdWriteService<T extends ApiVersionable> extends BasicKeyIdWriteService<T> {
    
}
