package com.github.bordertech.flux.wc.view.dumb;

import com.github.bordertech.flux.wc.view.dumb.form.FormUpdateable;
import com.github.bordertech.flux.wc.view.FluxDumbView;

/**
 * Form input via search view. The view bean is the input value.
 *
 * @param <T> the option type
 * @author Jonathan Austin
 * @since 1.0.0
 *
 */
public interface InputViaSearchView<T> extends FluxDumbView<T>, FormUpdateable {

}
