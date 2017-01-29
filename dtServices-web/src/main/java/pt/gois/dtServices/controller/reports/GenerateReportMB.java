package pt.gois.dtServices.controller.reports;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@ManagedBean
@ViewScoped
public class GenerateReportMB {

	public void generateReport(String fileName) {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		Connection connection = new ConnectionFactory().getConnectionFactory();
		
		
		GeradorRelatorio gerador = new GeradorRelatorio(fileName, parametros, connection);
		
		
		FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

        //pega o caminho do arquivo .jasper da aplicação
        String caminhoRelatorio = "/";
        InputStream reportStream = (InputStream) context.getExternalContext().getResourceAsStream(caminhoRelatorio);


        response.setHeader("Content-Disposition", "attachment; filename="+ fileName +".pdf");
        response.setContentType("application/download");
        response.setHeader("Pragma", "no-cache");
        try {
            System.out.println("------Inicio Try!-------");     
            ServletOutputStream servletOutputStream = response.getOutputStream();

    		gerador.geraPDFToOutputStream(servletOutputStream);

//            //envia para o navegador o PDF gerado
//            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parametros, fonteDados);
            servletOutputStream.flush();
            servletOutputStream.close();

            System.out.println("-----Passou Flush e Close!-----");
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
