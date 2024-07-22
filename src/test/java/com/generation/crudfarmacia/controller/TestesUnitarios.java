package com.generation.crudfarmacia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.crudfarmacia.model.Categoria;
import com.generation.crudfarmacia.model.Produto;
import com.generation.crudfarmacia.repository.CategoriaRepository;
import com.generation.crudfarmacia.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestesUnitarios {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired 
	private ProdutoRepository produtoRepository;
	
	@Test
	@Transactional
	@DisplayName("Cadastrar uma Categoria")
	public void testCreateCategoria() {
	    Categoria categoria = new Categoria(null, "Medicamentos");
	    Categoria savedCategoria = categoriaRepository.save(categoria);
	    assertNotNull(savedCategoria.getId());
	    assertEquals("Medicamentos", savedCategoria.getTipo());
	}

	@Test
	@Transactional
	@DisplayName("Cadastrar um Produto")
	public void testCreateProduto() {
		Categoria categoria = new Categoria(null, "Medicamentos");
		Categoria savedCategoria = categoriaRepository.save(categoria);
		
		Produto produto = new Produto(null, "Aspirina", "Para dor de cabeça", "Bayer", 15.99, savedCategoria);
		Produto savedProduto = produtoRepository.save(produto);
		
		assertNotNull(savedProduto.getId());
        assertEquals("Aspirina", savedProduto.getNome());
        assertEquals("Para dor de cabeça", savedProduto.getDescricao());
        assertEquals("Bayer", savedProduto.getMarca());
        assertEquals(15.99, savedProduto.getPreco());
        assertEquals(savedCategoria.getId(), savedProduto.getCategoria().getId());
	}
	
}
