# UpdateEmployeeByID
PUT REST for Update Employee by Id.

This REST is using dynamo mapper to save the data ( this function automatically creates or updates the table ).


End Point is : https://vsdc77v5ph.execute-api.us-west-2.amazonaws.com/prod/updateemployee?id=1 .

Sample JSON : 
{"employeeId": 1, "employeeStatus": "Active", "employeeFName" :"Himantcheck44444", "employeeMName" :"N/A", "employeeLName": "Gupta", "employeeBDay":"11-Oct-1996", "employeeJoinDate": "10-Jan-2016" }
