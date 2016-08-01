package pt.gois.dtServices.business;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import pt.gois.dtServices.entity.Log;
import pt.gois.dtServices.util.dtServicesConstants;

@Local
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public interface LogSBLocal extends GeneralSBLocal<Log>{
	public void create(Object entity);
	public void writeLog(String msg, Object [] params );
	public void writeLog(dtServicesConstants.LOG log, String msg, Object [] params );
	
}
