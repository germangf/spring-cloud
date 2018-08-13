/*
 * Copyright(c) 2013 BBVA (Suiza) SA All Rights Reserved.
 *
 * This software is the confidential and proprietary information of BBVA (Suiza) SA
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with BBVA (Suiza) SA
 *
 * Created on 17.03.2014
 *
 */
package ch.ggf.resource.avaloq.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ch.ggf.common.domain.BP;
import ch.ggf.common.domain.BP.Bundling;
import ch.ggf.common.domain.BP.Package;
import ch.ggf.common.domain.Employee;

/**
 * @author mansor
 *
 */
public class BPMapper implements RowMapper<BP> {

  /**
   * Maps the sql fields with the java properties.
   */
  public BPMapper() {
  }

  @Override
  public BP mapRow(ResultSet rs, int rowNum) throws SQLException {
    BP bp = new BP();
    bp.setBpNr(rs.getString("bp_nr"));
    bp.setAlias(rs.getString("bp_name"));
    bp.setLendingValue(rs.getBigDecimal("lv"));
    bp.setMarketValue(rs.getBigDecimal("mv"));
    bp.setRisk(rs.getBigDecimal("risk"));
    bp.setCurrency(rs.getString("bp_ref_curry"));
    bp.setBundling(Bundling.get(rs.getString("bundling")));
    bp.setPackage(Package.get(rs.getString("package")));

    Employee manager = new Employee();
    manager.setWindowsName(rs.getString("manager"));
    bp.setManager(manager);

    return bp;
  }

}
