package updateemployeebyid;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UpdateEmployeeLambdaFunctionHandler implements RequestStreamHandler, RequestHandler<Object, Object>{

	private DynamoDB dynamoDb;
    private String DYNAMODB_TABLE_NAME = "Employee";
    private Regions REGION = Regions.US_WEST_2;
    private AmazonDynamoDBClient client;
    
	@Override
	public Object handleRequest(Object arg0, Context arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
    public void handleRequest(InputStream input, OutputStream outputStream, Context context) throws JsonProcessingException, IOException {
        context.getLogger().log("Input: " + input);
        initDynamoDbClient();
        final ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(input);
       
        Employee updatedEmployee = new Employee();
        String id = json.path("params").path("querystring").path("id").asText();
        updatedEmployee.setEmployeeId(Integer.parseInt(id));
        updatedEmployee.setEmployeeStatus(json.path("body-json").path("employeeStatus").asText());
        updatedEmployee.setEmployeeFName(json.path("body-json").path("employeeFName").asText());
        updatedEmployee.setEmployeeMName(json.path("body-json").path("employeeMName").asText());
        updatedEmployee.setEmployeeLName(json.path("body-json").path("employeeLName").asText());
        updatedEmployee.setEmployeeBDay(json.path("body-json").path("employeeBDay").asText());
        updatedEmployee.setEmployeeJoinDate(json.path("body-json").path("employeeJoinDate").asText());
        
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        mapper.save(updatedEmployee);
        
        try {
    		outputStream.write(("Updated data successfully. You can do get employee to see the new data").getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
        } 
        
        
    }
 // -----------------------INITIATE DB CLIENT ----------------------------------------
    public void initDynamoDbClient() {
        client = new AmazonDynamoDBClient();
        client.setRegion(com.amazonaws.regions.Region.getRegion(REGION));
        this.dynamoDb = new DynamoDB(client);
    }


     
}
