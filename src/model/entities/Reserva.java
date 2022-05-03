package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.excecao.DominioExcecao;

public class Reserva {

	private Integer numeroQuarto;
	private Date dataEntrada;
	private Date dataSaida;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reserva(Integer numeroQuarto, Date dataEntrada, Date dataSaida){
		if (!dataSaida.after(dataEntrada)) {
			throw new DominioExcecao("A data de saída deve ser depois da data de entrada! ");
		}
		this.numeroQuarto = numeroQuarto;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Integer getNumeroQuarto() {
		return numeroQuarto;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public long duracao() {
		long dif = dataSaida.getTime() - dataEntrada.getTime();
		return TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS);
	}

	public void atualizarData(Date dataEntrada, Date dataSaida){
		Date gora = new Date();
		if (dataEntrada.before(gora) || dataSaida.before(gora)) {
			throw new DominioExcecao("A reserva deve ser para uma data futura! ");
		}
		if (!dataSaida.after(dataEntrada)) {
			throw new DominioExcecao("A data de saída deve ser depois da data de entrada! ");
		}
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
	}

	@Override
	public String toString() {
		return "Quarto " 
				+ numeroQuarto
				+ ", data de entrada: " 
				+ sdf.format(dataEntrada) 
				+ ", data de saída: "		
				+ sdf.format(dataSaida) 
				+ ", " 
				+ duracao()
				+ " noites";
	}
}
