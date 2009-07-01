package org.richfaces.demo.modifiableModel;


import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * @author Nick Belaevski
 * @since 3.3.2
 */
@Scope(ScopeType.STATELESS)
@Name("hibernateDataModel")
public class HibernateDataModel extends BaseModifiableHibernateDataModel<DataItem> {

	@In
	private Session hibernateSession;

	public HibernateDataModel() {
		super(DataItem.class);
	}

	@Override
	protected Session getSession() {
		return hibernateSession;
	}

}
