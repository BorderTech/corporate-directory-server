package com.github.bordertech.wcomponents.lib.security;

import java.io.Serializable;
import java.util.Set;

/**
 * Application servlet paths.
 *
 * @author Jonathan Austin
 */
public interface AppPath extends Serializable {

	String getPath();

	Set<AppRole> getAppRoles();

}
