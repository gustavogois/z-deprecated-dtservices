package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.Log;
import pt.gois.dtServices.util.dtServicesConstants;

@Stateless
public class LogSB extends GeneralSB<Log> implements LogSBLocal{

	public void create(Object entity) {
		getEM().persist(entity);
	}
	
	public void writeLog(String msg, Object [] params ){
//		String userName = SecurityContextAssociation.getPrincipal().getName();
		writeLog(dtServicesConstants.LOG.INFO, msg, params );
	}
	
	public void writeLog(dtServicesConstants.LOG log, String msg, Object [] params ){
		create(new Log(log.toString(), String.format(msg, params)));
	}
	
	public LogSB() {
		super(Log.class);
	}

}
