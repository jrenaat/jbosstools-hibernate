package org.jboss.tools.hibernate.runtime.v_5_5.internal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.mapping.OneToMany;
import org.hibernate.mapping.Set;
import org.hibernate.mapping.SimpleValue;
import org.hibernate.mapping.Value;
import org.jboss.tools.hibernate.runtime.common.IFacade;
import org.jboss.tools.hibernate.runtime.common.IFacadeFactory;
import org.jboss.tools.hibernate.runtime.spi.IValue;
import org.jboss.tools.hibernate.runtime.v_5_5.internal.util.DummyMetadataBuildingContext;
import org.junit.jupiter.api.Test;

public class ValueFacadeTest {

	private static IFacadeFactory FACADE_FACTORY = new FacadeFactoryImpl();
	
	private Value valueTarget = null;
	private IValue valueFacade = null;
	
	@Test
	public void testIsSimpleValue() {
		valueTarget = new SimpleValue(DummyMetadataBuildingContext.INSTANCE, null);
		valueFacade = FACADE_FACTORY.createValue(valueTarget);
		assertTrue(valueFacade.isSimpleValue());
		valueTarget = new Set(DummyMetadataBuildingContext.INSTANCE, null);
		valueFacade = FACADE_FACTORY.createValue(valueTarget);
		assertFalse(valueFacade.isSimpleValue());
	}

	@Test
	public void testIsCollection() {
		valueTarget = new SimpleValue(DummyMetadataBuildingContext.INSTANCE, null);
		valueFacade = FACADE_FACTORY.createValue(valueTarget);
		assertFalse(valueFacade.isCollection());
		valueTarget = new Set(DummyMetadataBuildingContext.INSTANCE, null);
		valueFacade = FACADE_FACTORY.createValue(valueTarget);
		assertTrue(valueFacade.isCollection());
	}

	@Test
	public void testGetCollectionElement() {
		valueTarget = new SimpleValue(DummyMetadataBuildingContext.INSTANCE, null);
		valueFacade = FACADE_FACTORY.createValue(valueTarget);
		IValue collectionElement = valueFacade.getCollectionElement();
		assertNull(collectionElement);
		Set set = new Set(DummyMetadataBuildingContext.INSTANCE, null);
		set.setElement(valueTarget);
		valueFacade = FACADE_FACTORY.createValue(set);
		collectionElement = valueFacade.getCollectionElement();
		assertNotNull(collectionElement);
		assertSame(valueTarget, ((IFacade)collectionElement).getTarget());
	}

	@Test 
	public void testIsOneToMany() {
		valueTarget = new SimpleValue(DummyMetadataBuildingContext.INSTANCE, null);
		valueFacade = FACADE_FACTORY.createValue(valueTarget);
		assertFalse(valueFacade.isOneToMany());
		OneToMany oneToMany = new OneToMany(DummyMetadataBuildingContext.INSTANCE, null);
		valueFacade = FACADE_FACTORY.createValue(oneToMany);
		assertTrue(valueFacade.isOneToMany());
	}

}