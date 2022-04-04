package com.example.ejercicioTest1.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.ejercicioTest1.model.Articulo;

@RunWith(MockitoJUnitRunner.class)
public class CarritoCompraServiceImplTest {
	
	@InjectMocks
	private CarritoCompraServiceImpl carritoServiceImpl = new CarritoCompraServiceImpl();
	
	@Mock
	private BaseDatosI baseDatos;

	@Test
	public void testLimpiarCesta() {
		carritoServiceImpl.addArticulo(new Articulo("Camiseta", 20.0));
		assertFalse(carritoServiceImpl.getArticulos().isEmpty());
		carritoServiceImpl.limpiarCesta();
		assertTrue(carritoServiceImpl.getArticulos().isEmpty());
	}

	@Test
	public void testAddArticulo() {
		//Creamos un artículo.
		Articulo articulo = new Articulo("Sudadera", 25.00);
		//Comprobamos que el método de la base de datos devuelve 1.
		Mockito.when(baseDatos.insertarArticulo(articulo)).thenReturn(1);
		//Comprobamos que el método de la cesta devuelve 1.
		assertEquals(Integer.valueOf(1), carritoServiceImpl.addArticulo(articulo));
		//Comprobamos que el metodo se ejecuta al menos 1 vez.
		Mockito.verify(baseDatos, Mockito.atLeast(1)).insertarArticulo(articulo);
		
		List<Articulo> articulos = carritoServiceImpl.getArticulos();
		
		//Comprobamos que el nombre es correcto.
		assertEquals("Sudadera", articulos.get(0).getNombre());
		
		//Comprobamos que el precio es correcto.
		assertEquals(Double.valueOf(25D), articulos.get(0).getPrecio());
		
	}

	@Test
	public void testGetNumArticulos() {
		carritoServiceImpl.addArticulo(new Articulo("Camiseta", 20.0));
		int cantidadProductos = carritoServiceImpl.getNumArticulos();
		assertEquals("Hay 1 Articulo",1, cantidadProductos);
		carritoServiceImpl.limpiarCesta();
	}

	@Test
	public void testGetArticulos() {
		carritoServiceImpl.addArticulo(new Articulo("Camiseta", 20.0));
		int cantidadProductos = carritoServiceImpl.getNumArticulos();
		assertEquals("Hay 1 Articulo",1, cantidadProductos);
		carritoServiceImpl.limpiarCesta();
	}

	@Test
	public void testTotalPrice() {
		carritoServiceImpl.addArticulo(new Articulo("Camiseta", 20.00));
		carritoServiceImpl.addArticulo(new Articulo("Pantalón", 15.00));
		Double precioTotal = carritoServiceImpl.totalPrice();
		//System.out.println(precioTotal);
		assertEquals(Double.valueOf(35D), precioTotal);
	}

	@Test
	public void testCalcularDescuento() {
		
		Double descuento = carritoServiceImpl.calcularDescuento(20.00, 20.00);
		assertEquals(Double.valueOf(16D), descuento);
	}
	
	@Test
	public void testAplicarDescuento() {
		
		Articulo articulo = new Articulo("Camiseta", 20.00);
		Mockito.when(baseDatos.buscarArticulo(2)).thenReturn(articulo);
		Double descuento = carritoServiceImpl.aplicarDescuento(2, 20.00);
		
		assertEquals(Double.valueOf(16D), descuento);
		
		Mockito.verify(baseDatos, Mockito.times(1)).buscarArticulo(2);
	}
}


