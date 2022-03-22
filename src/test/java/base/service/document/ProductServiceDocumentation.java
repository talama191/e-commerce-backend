package base.service.document;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;


import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
public class ProductServiceDocumentation {
	
	public static ResponseFieldsSnippet productResponseFieldDescription() {
        FieldDescriptor[] fieldDescriptors = {
        		fieldWithPath("[].id").description("The id algo."),
                fieldWithPath("[].name").description("The input format validate json."),
                fieldWithPath("[].price").description("The ouput format validate json"),
                fieldWithPath("[].img1").description("Technical name"),
                fieldWithPath("[].img2").description("Business name"),
                fieldWithPath("[].shortDescription").description("Dataframe input"),
                fieldWithPath("[].longDescription").description("Parameters input"),
                fieldWithPath("[].cartLinesDto").description("Category")
             
        };
        return relaxedResponseFields(fieldDescriptors);
        
	}
}
