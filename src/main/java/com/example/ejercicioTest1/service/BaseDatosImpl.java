package com.example.ejercicioTest1.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.ejercicioTest1.model.Articulo;

@Service
public class BaseDatosImpl implements BaseDatosI {

	private Map<Integer, Articulo> baseDatos;
	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		baseDatos = new HashMap<>();
		baseDatos.put(1, new Articulo("Pantalon", 25.00));
		baseDatos.put(2, new Articulo("Camiseta", 20.00));
		baseDatos.put(3, new Articulo("Chandal", 25.00));
		baseDatos.put(4, new Articulo("Zapatos", 10.00));
		
	}

	@Override
	public Integer insertarArticulo(Articulo articulo) {
		// TODO Auto-generated method stub
		
		//Añadimos el artículo al mapa con su ID
		baseDatos.put(baseDatos.size()+1, articulo);
		
		//Devuelve el ID del artículo.
		return baseDatos.size();
	}

	@Override
	public Articulo buscarArticulo(Integer identificador) {
		// TODO Auto-generated method stub
		return baseDatos.get(identificador);
	}

}
