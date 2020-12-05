package com.mitocode.service.Impl;

import com.mitocode.model.Producto;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IProductoRepo;
import com.mitocode.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl extends CRUDImpl<Producto, Integer> implements IProductoService {

	@Autowired
	private IProductoRepo repo;

	@Override
	protected IGenericRepo<Producto, Integer> getRepo() {
		return repo;
	}
	
	

}
