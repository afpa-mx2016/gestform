/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestform;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.lduboeuf.gestform.model.dao.ConnectDB;



/**
 *
 * @author lionel
 */
public class TestReport {
    
    public static void main(String[] args) {
        

        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(TestReport.class.getResource("/reports/ecf_result.jasper"));
            report.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "false");
            report.setProperty("net.sf.jasperreports.default.font.name=SansSerif", "true");
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("formation_code", new String("DL16"));
            
            JasperPrint jPrint = JasperFillManager.fillReport(report, params, ConnectDB.getConnection());
            JasperExportManager.exportReportToPdfFile(jPrint, "/tmp/sample.pdf");
        
        
        } catch (JRException ex) {
            Logger.getLogger(TestReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
