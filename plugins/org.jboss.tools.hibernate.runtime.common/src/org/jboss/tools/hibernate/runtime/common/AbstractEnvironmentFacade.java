package org.jboss.tools.hibernate.runtime.common;

import org.jboss.tools.hibernate.runtime.spi.IEnvironment;
import org.jboss.tools.hibernate.runtime.spi.IFacadeFactory;

public abstract class AbstractEnvironmentFacade 
extends AbstractFacade 
implements IEnvironment {

	public AbstractEnvironmentFacade(
			IFacadeFactory facadeFactory, 
			Object target) {
		super(facadeFactory, target);
	}

	@Override
	public String getTransactionManagerStrategy() {
		return (String) Util.getFieldValue(
				getEnvironmentClass(), 
				"TRANSACTION_MANAGER_STRATEGY", 
				null);
	}
	
	protected Class<?> getEnvironmentClass() {
		return Util.getClass(getEnvironmentClassName(), getFacadeFactoryClassLoader());
	}

	protected String getEnvironmentClassName() {
		return "org.hibernate.cfg.Environment";
	}
	
}
