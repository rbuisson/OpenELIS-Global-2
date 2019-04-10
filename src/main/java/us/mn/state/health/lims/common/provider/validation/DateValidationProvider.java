/**
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package us.mn.state.health.lims.common.provider.validation;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.mn.state.health.lims.common.servlet.validation.AjaxServlet;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator.DateRelation;

public class DateValidationProvider extends BaseValidationProvider {

	public DateValidationProvider() {
		super();
	}

	public DateValidationProvider(AjaxServlet ajaxServlet) {
		this.ajaxServlet = ajaxServlet;
	}

	@Override
	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get id from request
		String dateString = request.getParameter("date");
		String relative = request.getParameter("relativeToNow");
		String formField = request.getParameter("field");

		String result = INVALID;

		if (DateUtil.yearSpecified(dateString)) {
			dateString = DateUtil.normalizeAmbiguousDate(dateString);
			Date date = getDate(dateString);
			result = validateDate(date, relative);
		}
		ajaxServlet.sendData(formField, result, request, response);
	}

	public Date getDate(String date) {
		return CustomDateValidator.getInstance().getDate(date);
	}

	public String validateDate(Date date, String relative) {
		return CustomDateValidator.getInstance().validateDate(date, DateRelation.valueOf(relative.toUpperCase()));
	}

}
