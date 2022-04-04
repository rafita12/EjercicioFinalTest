package com.example.ejercicioTest1.service;

import org.springframework.stereotype.Service;

import com.example.ejercicioTest1.model.Articulo;


public interface BaseDatosI {
	
	public void iniciar();
	
	public Integer insertarArticulo(Articulo articulo);

	public Articulo buscarArticulo(Integer identificador);

}
