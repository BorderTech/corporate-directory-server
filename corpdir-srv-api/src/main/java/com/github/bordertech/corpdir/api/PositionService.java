package com.github.bordertech.corpdir.api;

import com.github.bordertech.corpdir.api.data.Contact;
import com.github.bordertech.corpdir.api.data.OrgUnit;
import com.github.bordertech.corpdir.api.data.Position;
import java.util.List;

/**
 * Position Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface PositionService {

	Position getPosition(final String positionKeyId);

	List<Position> getReportPositions(final String positionKeyId);

	List<Contact> getAssignedContacts(final String positionKeyId);

	List<OrgUnit> getManagesOrgUnits(final String positionKeyId);

	String createPosition(final Position position);

	Position updatePosition(final String positionKeyId, final Position position);

	void deletePosition(final String positionKeyId);

	void assignContact(final String positionKeyId, final String contactKeyId);

	void unassignContact(final String positionKeyId, final String contactKeyId);

	void assignPositionToPosition(final String positionKeyId, final String reportToPositionKeyId);

}
