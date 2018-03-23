package com.github.bordertech.corpdir.jpa.readonly.common.svc;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;
import com.github.bordertech.corpdir.api.service.readonly.BasicKeyIdReadOnlyService;
import com.github.bordertech.corpdir.jpa.common.feature.PersistKeyIdObject;
import javax.inject.Singleton;

/**
 * Keyed Entity JPA read-only service implementation.
 *
 * @param <A> the API object type
 * @param <P> the entity type
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public abstract class JpaBasicKeyIdReadOnlyService<A extends ApiKeyIdObject, P extends PersistKeyIdObject> extends JpaBasicIdReadOnlyService<A, P> implements BasicKeyIdReadOnlyService<A> {
    
}
