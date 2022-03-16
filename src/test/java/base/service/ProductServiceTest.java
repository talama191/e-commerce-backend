package base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;


import base.model.dto.CategoryDto;
import base.model.dto.ProductDto;



public class ProductServiceTest {
	@Autowired
	private ProductService productSerice;
	
       @Test
       public void test_getListProduct_thenReturnOk() throws ExecutionException, InterruptedException{
    	   ProductDto product1 = new ProductDto();
    	   product1.setId(2);
    	   product1.setName("POCKET WATCH");
    	   product1.setPrice((long)16);
    	   product1.setImg1("https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl2-330x421.jpg");
    	   product1.setImg2("https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image2xxl6-235x300.jpg");
           CategoryDto category = new CategoryDto();
           category.setId(1);
           category.setName("ACCESSORIES");
    	   product1.setCategory(category);
    	   
    	   List<ProductDto> list = new ArrayList<ProductDto>();
    	   list.add(product1);
    	   //test
    	   when(productSerice.findAll()).thenReturn(list);
    	   
    	   List<ProductDto> proList = productSerice.findAll();
    	   
    	   assertEquals(31, proList.size());   
       } 

}
