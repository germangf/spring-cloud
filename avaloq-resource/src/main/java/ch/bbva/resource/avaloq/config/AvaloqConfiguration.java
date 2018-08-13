/*
 * Copyright(c) 2013 BBVA (Suiza) SA All Rights Reserved.
 *
 * This software is the confidential and proprietary information of BBVA (Suiza) SA
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with BBVA (Suiza) SA
 *
 * Created on 07.05.2015
 *
 */
package ch.ggf.resource.avaloq.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import ch.ggf.resource.avaloq.dao.impl.MISDaoImpl;
import ch.ggf.resource.avaloq.dao.impl.MISTrxDaoImpl;
import ch.ggf.resource.avaloq.dao.impl.OwnershipDSDaoImpl;

/**
 * @author mansor
 *
 */
@Configuration
@ComponentScan("ch.ggf.resource.avaloq")
@PropertySource("${avaloq.properties}")
public class AvaloqConfiguration {

  @Value("${jdbc.avaloq.driver}")
  private String driver;

  @Value("${jdbc.avaloq.url}")
  private String url;

  @Value("${jdbc.avaloq.username}")
  private String username;

  @Value("${jdbc.avaloq.password}")
  private String password;

  @Value("${avaloq.linked.server}")
  private String linkedServer;

  @Bean
  public BasicDataSource avaloqDataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    dataSource.setDriverClassName(driver);
    return dataSource;
  }

  @Bean
  public MISDaoImpl avaloqDaoImpl() {
    return new MISDaoImpl(avaloqDataSource());
  }

  @Bean
  public MISTrxDaoImpl avaloqTrxDaoImpl() {
    return new MISTrxDaoImpl(avaloqDataSource());
  }

  @Bean
  public OwnershipDSDaoImpl certDSDaoImpl() {
    return new OwnershipDSDaoImpl(avaloqDataSource(), linkedServer);
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySources() {
    return new PropertySourcesPlaceholderConfigurer();
  }

}
