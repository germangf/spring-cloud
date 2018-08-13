/*
 * Copyright(c) 2013 BBVA (Suiza) SA All Rights Reserved.
 *
 * This software is the confidential and proprietary information of BBVA (Suiza) SA
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with BBVA (Suiza) SA
 *
 * Created on 01.11.2013
 *
 */
package ch.ggf.resource.avaloq.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import ch.ggf.common.domain.BP;
import ch.ggf.common.domain.Container;
import ch.ggf.common.domain.Employee;

/**
 * Implementation of {@code ResultSetExtractor} for extracting results from a
 * {@link java.sql.ResultSet}.
 *
 * Returns a {@code Account}.
 *
 * @author mansor
 *
 */
public class BPResultSetExtractor implements ResultSetExtractor<BP> {

  @Override
  public BP extractData(ResultSet rs) throws SQLException {

    BP bp = null;
    while (rs.next()) {
      if (bp == null) {
        bp = extractBP(rs);
      }
      Container portfolio = extractContainer(rs);
      if (bp != null) {
        bp.addContainer(portfolio);
      }
    }

    return bp;
  }

  private BP extractBP(ResultSet rs) throws SQLException {
    BP bp = new BP();
    bp.setBpNr(rs.getString("bpNr"));
    bp.setAlias(rs.getString("pseudonym"));
    bp.setAmlRisk(rs.getString("aml_risk"));

    Employee manager = new Employee();
    manager.setWindowsName(rs.getString("windowsName"));
    manager.setCitrixName(rs.getString("citrixName"));

    // bp.setManager(manager);
    return bp;
  }

  private Container extractContainer(ResultSet rs) throws SQLException {
    Container portfolio = new Container();
    portfolio.setContainerNr(rs.getString("id_portfolio"));
    // portfolio.setAlias(rs.getString("person_nr"));
    // portfolio.setCurrency(rs.getString("person_nr"));
    return portfolio;
  }

}
