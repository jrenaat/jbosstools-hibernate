package org.jboss.tools.hibernate.runtime.v_6_1.internal.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Properties;

import org.hibernate.tool.api.reveng.RevengStrategy;
import org.hibernate.tool.internal.reveng.strategy.DefaultStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JdbcMetadataConfigurationTest {

	private JdbcMetadataConfiguration jdbcMetadataConfiguration = null;
	
	@BeforeEach
	public void beforeEach() {
		jdbcMetadataConfiguration = new JdbcMetadataConfiguration();
	}
	
	@Test
	public void testInstance() {
		assertNotNull(jdbcMetadataConfiguration);
	}
	
	@Test
	public void testGetProperties() {
		Properties properties = new Properties();
		assertNotNull(jdbcMetadataConfiguration.properties);
		assertNotSame(properties,  jdbcMetadataConfiguration.getProperties());
		jdbcMetadataConfiguration.properties = properties;
		assertSame(properties, jdbcMetadataConfiguration.getProperties());
	}
	
	@Test
	public void testSetProperties() {
		Properties properties = new Properties();
		assertNotNull(jdbcMetadataConfiguration.properties);
		assertNotSame(properties,  jdbcMetadataConfiguration.getProperties());
		jdbcMetadataConfiguration.setProperties(properties);
		assertSame(properties, jdbcMetadataConfiguration.getProperties());
	}
	
	@Test
	public void testGetProperty() {
		assertNull(jdbcMetadataConfiguration.getProperty("foo"));
		jdbcMetadataConfiguration.properties.put("foo", "bar");
		assertEquals("bar", jdbcMetadataConfiguration.getProperty("foo"));
	}

	@Test
	public void testSetProperty() {
		assertNull(jdbcMetadataConfiguration.properties.get("foo"));
		jdbcMetadataConfiguration.setProperty("foo", "bar");
		assertEquals("bar", jdbcMetadataConfiguration.properties.get("foo"));
	}
	
	@Test
	public void testAddProperties() {
		Properties properties = new Properties();
		properties.put("foo", "bar");
		assertNull(jdbcMetadataConfiguration.properties.get("foo"));
		jdbcMetadataConfiguration.addProperties(properties);
		assertEquals("bar", jdbcMetadataConfiguration.properties.get("foo"));
	}
	
	@Test
	public void testGetReverseEngineeringStrategy() {
		RevengStrategy strategy = new DefaultStrategy();
		assertNull(jdbcMetadataConfiguration.getReverseEngineeringStrategy());
		jdbcMetadataConfiguration.revengStrategy = strategy;
		assertSame(strategy, jdbcMetadataConfiguration.getReverseEngineeringStrategy());
	}
	
}
