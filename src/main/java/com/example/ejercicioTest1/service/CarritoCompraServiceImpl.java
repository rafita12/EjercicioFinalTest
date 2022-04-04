package com.example.ejercicioTest1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ejercicioTest1.model.Articulo;

public class CarritoCompraServiceImpl implements CarritoCompraService {

	@Autowired
	private BaseDatosI baseDatos;
	
	
	List<Articulo> cesta = new ArrayList<>();
	
	@Override
	public void limpiarCesta() {
		// TODO Auto-generated method stub
		cesta.clear();
		
	}

	//Método del último ejercicio.
	@Override
	public Integer addArticulo(Articulo articulo) {
		// TODO Auto-generated method stub
		//baseDatos.iniciar();
		//Añadimos el articulo a la cesta
		cesta.add(articulo);
		
		//Sacamos el ID del articulo que hemos añadido
		int idArticulo = baseDatos.insertarArticulo(articulo);
		
		//Devolvemos el ID
		return idArticulo;
		
	}

	@Override
	public Integer getNumArticulos() {
		// TODO Auto-generated method stub
		return cesta.size();
	}

	@Override
	public List<Articulo> getArticulos() {
		// TODO Auto-generated method stub
		return cesta;
	}

	@Override
	public Double totalPrice() {
		// TODO Auto-generated method stub
		Double total = 0.0;
		
		for (Articulo articulo : cesta) {
			total += articulo.getPrecio();
		}
		return total;
	}

	@Override
	public Double calcularDescuento(Double precio, Double porcentaje) {
		// TODO Auto-generated method stub
		return precio - precio*porcentaje/100;
	}

	@Override
	public Double aplicarDescuento(Integer identificador, Double porcentaje) {
		// TODO Auto-generated method stub
		Articulo articulo = baseDatos.buscarArticulo(identificador);
		
		if (Optional.ofNullable(articulo).isPresent()) {
			return calcularDescuento(articulo.getPrecio(), porcentaje);
		}else {
			System.out.println("No se encuentra el articulo con ID: " + identificador);
		}
		
		return 0D;
	}

}
