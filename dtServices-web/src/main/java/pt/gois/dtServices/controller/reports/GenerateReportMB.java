package pt.gois.dtServices.controller.reports;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pt.gois.dtServices.report.VisaoGeralReport;

@ManagedBean
@ViewScoped
public class GenerateReportMB {

	public void generateReport(String fileName) throws JRException {
		JasperCompileManager.compileReportToFile("visao-geral-processos.jrxml");
		
		List<VisaoGeralReport> visaoGeralReportList = new ArrayList<VisaoGeralReport>(); 
		JRDataSource dataSource = new JRBeanCollectionDataSource(visaoGeralReportList);
    }
}
