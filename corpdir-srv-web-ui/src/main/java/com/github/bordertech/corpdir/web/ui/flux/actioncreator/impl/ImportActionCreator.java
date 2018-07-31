package com.github.bordertech.corpdir.web.ui.flux.actioncreator.impl;

import com.github.bordertech.corpdir.web.ui.CorpEntityType;
import com.github.bordertech.flux.actioncreator.impl.DefaultActionCreator;
import javax.inject.Inject;

/**
 *
 * @author aswinkandula
 */
public class ImportActionCreator extends DefaultActionCreator {

    @Inject
    public ImportActionCreator() {
	super(CorpEntityType.IMPORT.getActionCreatorKey());
    }
}
