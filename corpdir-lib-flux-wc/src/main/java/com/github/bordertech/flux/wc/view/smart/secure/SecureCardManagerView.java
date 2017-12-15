package com.github.bordertech.flux.wc.view.smart.secure;

import com.github.bordertech.flux.wc.view.FluxSmartView;
import com.github.bordertech.wcomponents.lib.security.SecureCardManager;

/**
 * Smart View that is a secure card manager.
 *
 * @author jonathan
 * @param <T> the view bean type
 * @param <V> the secure card type
 */
public interface SecureCardManagerView<T, V extends SecureCardView> extends FluxSmartView<T>, SecureCardManager<V> {

}
