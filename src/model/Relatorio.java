/*package model;

import java.time.LocalDateTime;

public class Relatorio {
	
	private String tipo_relatorio;
	private LocalDateTime dataGeracao;
	private String dados;
	
	//Getters e Setters
	//
	public String getTipo_relatorio() {
		return tipo_relatorio;
	}

	public void setTipo_relatorio(String tipo_relatorio) {
		this.tipo_relatorio = tipo_relatorio;
	}
	
	//
	public LocalDateTime getDataGeracao() {
		return dataGeracao;
	}
	
	public void setDataGeracao(LocalDateTime dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	
	//
	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	
	//Metodos
	public void gerarRelatorioGeral(){
		
	}
	
	public void exportarParaExcel(){
		
	}
}
*/

package model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class Relatorio {

    private String tipo_relatorio;
    private LocalDateTime dataGeracao;
    private String dados;

    // Getters e Setters
    public String getTipo_relatorio() {
        return tipo_relatorio;
    }

    public void setTipo_relatorio(String tipo_relatorio) {
        this.tipo_relatorio = tipo_relatorio;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    // Método para exportar para Excel
    public void exportarParaExcel(String caminhoArquivo) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Relatório");

        // Criar cabeçalho
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Tipo do Relatório");
        headerRow.createCell(1).setCellValue("Data de Geração");
        headerRow.createCell(2).setCellValue("Conteúdo");

        // Preencher dados
        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(tipo_relatorio);
        dataRow.createCell(1).setCellValue(dataGeracao.toString());
        dataRow.createCell(2).setCellValue(dados);

        // Salvar arquivo
        try (FileOutputStream fileOut = new FileOutputStream(caminhoArquivo)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
