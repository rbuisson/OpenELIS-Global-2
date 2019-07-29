package org.openelisglobal.qaevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.qaevent.dao.QaObservationTypeDAO;
import org.openelisglobal.qaevent.valueholder.QaObservationType;

@Service
public class QaObservationTypeServiceImpl extends BaseObjectServiceImpl<QaObservationType, String> implements QaObservationTypeService {
	@Autowired
	protected QaObservationTypeDAO baseObjectDAO;

	QaObservationTypeServiceImpl() {
		super(QaObservationType.class);
	}

	@Override
	protected QaObservationTypeDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public QaObservationType getQaObservationTypeByName(String typeName) {
        return getBaseObjectDAO().getQaObservationTypeByName(typeName);
	}
}