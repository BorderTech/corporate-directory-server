package com.github.bordertech.corpdir.api.service;

import com.github.bordertech.corpdir.api.common.ApiVersionable;

/**
 * Basic read and write service for keyed API object that have version data.
 *
 * @param <T> the keyed API object
 * @author Jonathan Austin
 * 
 * @since 1.0.0
 */
public interface BasicVersionKeyIdService<T extends ApiVersionable> extends BasicVersionKeyIdReadOnlyService<T>, BasicKeyIdService<T> {
    
}
