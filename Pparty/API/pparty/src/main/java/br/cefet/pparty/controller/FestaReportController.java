package br.cefet.pparty.controller;

import br.cefet.pparty.model.Festa;
import br.cefet.pparty.service.FestaService;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/report/festa")
public class FestaReportController {

    private FestaService festaService;

    @Autowired
    public FestaReportController(FestaService festaService){
        this.festaService = festaService;
    }

    @GetMapping(
            value = "/todos",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<InputStreamResource> getFestaReport() throws IOException {
        File file = ResourceUtils.getFile("C:\\Users\\gabri\\OneDrive\\Documentos\\Escola - Tecnico\\git-PP\\Pparty\\API\\pparty\\src\\main\\resources\\report\\festa.jasper");
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = new ByteArrayOutputStream();

        List<Festa> festaList = festaService.consultarTodos();

        Map parameters = new HashMap();
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(festaList);
        byte [] byteReporte = null;
        try {
            JasperPrint print = JasperFillManager.fillReport(inputStream, parameters, beanColDataSource);
            byteReporte = JasperExportManager.exportReportToPdf(print);
        } catch (JRException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(new ByteArrayInputStream(byteReporte)));
    }

        @GetMapping(
            value = "/media",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<InputStreamResource> getFestaReportMedia() throws IOException {
        File file = ResourceUtils.getFile("C:\\Users\\gabri\\OneDrive\\Documentos\\Escola - Tecnico\\git-PP\\Pparty\\API\\pparty\\src\\main\\resources\\report\\festa.jasper");
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = new ByteArrayOutputStream();

        List<Festa> festaList = festaService.consultarporMedio();

        Map parameters = new HashMap();
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(festaList);
        byte [] byteReporte = null;
        try {
            JasperPrint print = JasperFillManager.fillReport(inputStream, parameters, beanColDataSource);
            byteReporte = JasperExportManager.exportReportToPdf(print);
        } catch (JRException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(new ByteArrayInputStream(byteReporte)));
    }    
    
    @GetMapping(
            value = "/grande",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<InputStreamResource> getFestaReportGrande() throws IOException {
        File file = ResourceUtils.getFile("C:\\Users\\gabri\\OneDrive\\Documentos\\Escola - Tecnico\\git-PP\\Pparty\\API\\pparty\\src\\main\\resources\\report\\festa.jasper");
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = new ByteArrayOutputStream();

        List<Festa> festaList = festaService.consultarporGrande();

        Map parameters = new HashMap();
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(festaList);
        byte [] byteReporte = null;
        try {
            JasperPrint print = JasperFillManager.fillReport(inputStream, parameters, beanColDataSource);
            byteReporte = JasperExportManager.exportReportToPdf(print);
        } catch (JRException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(new ByteArrayInputStream(byteReporte)));
    }   
    
    @GetMapping(
            value = "/tematica",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<InputStreamResource> getFestaReportTematica() throws IOException {
        File file = ResourceUtils.getFile("C:\\Users\\gabri\\OneDrive\\Documentos\\Escola - Tecnico\\git-PP\\Pparty\\API\\pparty\\src\\main\\resources\\report\\festa.jasper");

        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = new ByteArrayOutputStream();

        List<Festa> festaList = festaService.consultarporTematica();

        Map parameters = new HashMap();
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(festaList);
        byte [] byteReporte = null;
        try {
            JasperPrint print = JasperFillManager.fillReport(inputStream, parameters, beanColDataSource);
            byteReporte = JasperExportManager.exportReportToPdf(print);
        } catch (JRException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(new ByteArrayInputStream(byteReporte)));
    }    
    
    @GetMapping(
            value = "/diurna",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<InputStreamResource> getFestaReportDiurna() throws IOException {
        File file = ResourceUtils.getFile("C:\\Users\\gabri\\OneDrive\\Documentos\\Escola - Tecnico\\git-PP\\Pparty\\API\\pparty\\src\\main\\resources\\report\\festa.jasper");
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = new ByteArrayOutputStream();

        List<Festa> festaList = festaService.consultarporDiurna();

        Map parameters = new HashMap();
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(festaList);
        byte [] byteReporte = null;
        try {
            JasperPrint print = JasperFillManager.fillReport(inputStream, parameters, beanColDataSource);
            byteReporte = JasperExportManager.exportReportToPdf(print);
        } catch (JRException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(new ByteArrayInputStream(byteReporte)));
    }

    @GetMapping(
            value = "/{idUsuario}/usuario",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<InputStreamResource> getFestaReportUsuario(@PathVariable("idUsuario") int idUsuario) throws IOException {
        File file = ResourceUtils.getFile("C:\\Users\\gabri\\OneDrive\\Documentos\\Escola - Tecnico\\git-PP\\Pparty\\API\\pparty\\src\\main\\resources\\report\\festa.jasper");
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = new ByteArrayOutputStream();

        List<Festa> festaList = festaService.consultarporIdusuario(idUsuario);

        Map parameters = new HashMap();
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(festaList);
        byte [] byteReporte = null;
        try {
            JasperPrint print = JasperFillManager.fillReport(inputStream, parameters, beanColDataSource);
            byteReporte = JasperExportManager.exportReportToPdf(print);
        } catch (JRException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(new ByteArrayInputStream(byteReporte)));
    }
}
