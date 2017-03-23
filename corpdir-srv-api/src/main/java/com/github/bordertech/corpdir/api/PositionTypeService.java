package com.github.bordertech.corpdir.api;

import com.github.bordertech.corpdir.api.data.Position;
import com.github.bordertech.corpdir.api.data.PositionType;
import java.util.List;

/**
 * Position Type Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface PositionTypeService {

	List<PositionType> getPositionTypes();

	List<Position> getPositions(final String typeKeyId);

	PositionType getPositionType(final String typeKeyId);

	String createPositionType(final PositionType type);

	PositionType updatePositionType(final String typeKeyId, final PositionType type);

	void deletePositionType(final String typeKeyId);

}
