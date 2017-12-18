import java.io.File;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

@Path("/banktran/validate")
public class BankTranValidationMicroService {

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response validateXMLSchema(TRANSACTION trans){
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("conf:xsdPath"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
			
			// Transform XML into JSON			
			JSONObject xmlJSONObj = XML.toJSONObject(TRANSACTION_Object_String);
            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
            System.out.println(jsonPrettyPrintString);
			
        } catch (IOException e | SAXException e10 {
            Response.status(400).entity("Invalid Request").build(); 
        }
 
        Response.status(200).entity(trans).build();
    }
}