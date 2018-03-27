package com.github.bordertech.corpdir.web.api.resource.v1;

import com.github.bordertech.corpdir.api.response.BasicResponse;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.Location;
import com.github.bordertech.corpdir.modify.api.v1.LocationWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.LocationReadOnlyService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Location REST Resource.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
@Path(value = "v1/locations")
public class LocationResource implements LocationReadOnlyService, LocationWriteService {

	private final LocationReadOnlyService readImpl;
	private final LocationWriteService writeImpl;

	/**
	 * @param impl the service implementation
	 */
	@Inject
	public LocationResource(final LocationReadOnlyService readImpl, final LocationWriteService writeImpl) {
		this.readImpl = readImpl;
		this.writeImpl = writeImpl;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<List<Location>> search(@QueryParam("search") final String search) {
		return readImpl.search(search);
	}

	@GET
	@Path("/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<Location> retrieve(@PathParam("key") final String keyId) {
		return readImpl.retrieve(keyId);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<Location> create(final Location location) {
		return writeImpl.create(location);
	}

	@PUT
	@Path("/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<Location> update(@PathParam("key") final String keyId, final Location location) {
		return writeImpl.update(keyId, location);
	}

	@DELETE
	@Path("/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public BasicResponse delete(@PathParam("key") final String keyId) {
		return writeImpl.delete(keyId);
	}

	@GET
	@Path("/{key}/locations")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<List<Location>> getSubs(@PathParam("key") final String keyId) {
		return readImpl.getSubs(keyId);
	}

	@PUT
	@Path("/{key}/locations/{subKey}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<Location> addSub(@PathParam("key") final String keyId, @PathParam("subKey") final String subKeyId) {
		return writeImpl.addSub(keyId, subKeyId);
	}

	@DELETE
	@Path("/{key}/locations/{subKey}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<Location> removeSub(@PathParam("key") final String keyId, @PathParam("subKey") final String subKeyId) {
		return writeImpl.removeSub(keyId, subKeyId);
	}

	@Override
	public DataResponse<List<Location>> getRootItems() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
