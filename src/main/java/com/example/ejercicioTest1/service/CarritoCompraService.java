package com.example.ejercicioTest1.service;

import java.util.List;

import com.example.ejercicioTest1.model.Articulo;

public interface CarritoCompraService {
	
	public void limpiarCesta();
	
	public Integer addArticulo(Articulo articulo);
	
	public Integer getNumArticulos();
	
	public List<Articulo> getArticulos();
	
	public Double totalPrice();
	
	public Double calcularDescuento(Double precio, Double porcentaje);

	public Double aplicarDescuento(Integer identificador, Double porcentaje);
}
