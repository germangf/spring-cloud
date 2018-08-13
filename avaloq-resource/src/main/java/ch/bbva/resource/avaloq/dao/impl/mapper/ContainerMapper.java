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
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.google.common.base.Strings;

import ch.ggf.common.domain.BP;
import ch.ggf.common.domain.Container;
import ch.ggf.common.domain.Container.ContainerType;
import ch.ggf.common.domain.Container.InvestmentStrategy;
import ch.ggf.common.util.Dates;

/**
 * @author mansor
 *
 */
public class ContainerMapper implements RowMapper<Container> {

  /**
   * Maps the sql fields with the java properties.
   */
  public ContainerMapper() {
  }

  @Override
  public Container mapRow(ResultSet rs, int rowNum) throws SQLException {
    Container container = new Container();
    container.setContainerNr(rs.getString("container_nr"));
    container.setCurrency(rs.getString("currency"));
    container.setOpenDate(parseDateIfNotNull(rs.getString("open_date")));
    container.setCloseDate(parseDateIfNotNull(rs.getString("close_date")));
    container.setClassId(rs.getString("container_class_id"));
    // container.setLendingValue(rs.getBigDecimal("lv"));
    // container.setMarketValue(rs.getBigDecimal("mv"));
    // container.setRisk(rs.getBigDecimal("risk"));
    // container.setBpName(rs.getString("bp_name"));
    container.setType(ContainerType.get(rs.getString("container_type")));
    container.setInvestmentStrategy(InvestmentStrategy.get(rs.getString("investment_strategy")));

    // Manager manager = new Manager();
    // manager.setCitrixName(rs.getString("usuario_citrix"));
    // container.setManager(manager);
    BP bp = new BP();
    bp.setBpNr(rs.getString("bp_nr"));
    container.setBP(bp);

    return container;
  }

  private Date parseDateIfNotNull(String dateString) {
    if (Strings.isNullOrEmpty(dateString)) {
      return null;
    }
    return Dates.parse(dateString);
  }

}
