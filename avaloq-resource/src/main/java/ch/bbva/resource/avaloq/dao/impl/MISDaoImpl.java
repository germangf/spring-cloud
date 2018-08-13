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
package ch.ggf.resource.avaloq.dao.impl;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

import ch.ggf.common.domain.BP;
import ch.ggf.common.domain.Container;
import ch.ggf.resource.avaloq.dao.AbstractJdbcDao;
import ch.ggf.resource.avaloq.dao.MISDao;
import ch.ggf.resource.avaloq.dao.impl.mapper.BPMapper;
import ch.ggf.resource.avaloq.dao.impl.mapper.ContainerMapper;

/**
 * Implementation for generic operations on Apsys database information.
 *
 * @author garciag
 *
 */
public class MISDaoImpl extends AbstractJdbcDao implements MISDao {

  private final String bpQuery = "select bp_nr, bp_name, mv, lv, risk, manager, bp_ref_curry, bundling, package from v_ce_datos_bp where bp_nr = ?";

  private final String containerQuery = "select container_nr, currency, open_date, close_date, container_class_id, container_type, investment_strategy, bp_nr"
      + " from v_ce_datos_container where container_nr = ?";

  /**
   * Constructor.
   *
   * @param dataSource
   *          a {@code DataSource} object
   */
  @Autowired
  public MISDaoImpl(DataSource dataSource) {
    super(dataSource);
  }

  @Override
  public BP getBP(String bpNr) {
    // -- bp
    BP bp = getJdbcTemplate().queryForObject(bpQuery, new BPMapper(), bpNr);

    // -- get citrix user
    bp.getManager().setCitrixName(getJdbcTemplate().queryForObject(mappingWindowsNameQuery, String.class, bp.getManager().getWindowsName()));

    // -- container
    List<Map<String, Object>> data = getJdbcTemplate().queryForList(containersForBPQuery, bpNr);
    for (Map<String, Object> map : data) {
      // get only active containers
      if (map.get("close_date") == null) {
        Container container = new Container();
        container.setContainerNr((String) map.get("container_nr"));
        bp.addContainer(container);
      }
    }

    // -- aml
    bp.setAmlRisk(getJdbcTemplate().queryForObject(amlRiskForBPQuery, String.class, bpNr));

    return bp;
  }

  @Override
  public Container getContainer(String containerNr) {
    Object[] params = Lists.newArrayList(containerNr).toArray();
    return getJdbcTemplate().queryForObject(containerQuery, params, new ContainerMapper());
  }

}
