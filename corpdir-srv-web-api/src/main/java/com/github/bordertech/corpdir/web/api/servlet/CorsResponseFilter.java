package com.github.bordertech.corpdir.web.api.servlet;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
/**
 * Enable CORS on reset service responses.
 * @author Ricik Brown
 */
public class CorsResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext)
			throws IOException {

		MultivaluedMap<String, Object> headers = responseContext.getHeaders();

		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type");
	}
}
