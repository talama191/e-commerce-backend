package base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.checkerframework.checker.units.qual.min;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import base.model.dto.CategoryDto;
import base.model.dto.ProductDto;
import base.model.entity.Category;
import base.model.entity.Product;
import base.repository.CategoryRepository;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;



public class ProductServiceTest {
	@Autowired
	private ProductService productSerice;
	@Autowired
	private CategoryRepository cateRepo;
	@Autowired
	private MinioService minio;
	
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
       
       
       
       @Test
       public void test_getProductByCategoryName_thenReturnOK()  throws ExecutionException, InterruptedException{
    	   Category c = cateRepo.findByName("bags");
    	   assertEquals(5, c.getProducts().size());   
       }
       
       @Test
       public void test_getProductByWrongName()  throws ExecutionException, InterruptedException{
    	   Product p = productSerice.findByName("test-name");
    	   assertEquals(null,p);   
       }
       
       @Test
       public void test_addProductWrongImageType() throws ExecutionException, InterruptedException, InvalidKeyException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidResponseException, XmlParserException, InternalException, IOException{
    	   Product p = new Product();
    	   p.setName("test");
    	   p.setPrice((long)1);
    	   MultipartFile file = new MultipartFile() {
			
			@Override
			public void transferTo(File dest) throws IOException, IllegalStateException {
				// TODO Auto-generated method stub
				dest = new File("img1.zip");
				
			}

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getOriginalFilename() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public long getSize() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public byte[] getBytes() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public InputStream getInputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
		
		};
    	   p.setImg1(minio.upload(file, "bucket"));
    	   Category cat = new Category();
    	   cat.setId(1);
    	   cat.setName("ACCESORIES");
    	   
//    	   assertEquals(null, productSerice.save(p));
    	   
       }
       
       
       
       
       
       
       

}
