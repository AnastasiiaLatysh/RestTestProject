package main.test;

import main.controllers.TestData;
import org.junit.Before;
import org.junit.Test;

public class AddUserTests extends TestBase {

    @Before
    public void addUser(){}

    @Test
    public void testAddContact() {
		/*`
		 * Performs POST operation on http://<host>:<port>/<endpoint>
		 * and add new client
		 * and check correctness of returned information
		 */
        executor.post(TestData.generalEndPoint, TestData.userJsonData.get("jsonContent"), TestData.contentType)
                .expectCode(201)            // Expected code
                .expectMessage(TestData.statusCodeMessages.get("201"))          // Expected Message
                .expectHeader(TestData.contentTypeKey, TestData.contentType)    // Content-Type header value
                .expectInBody(TestData.userData.get("userEmail")) // Response body contains user email
                .expectInBody(TestData.userData.get("userName")) // Response body contains user name
                .expectInBody(TestData.userData.get("userLastName")); // Response body contains user last name
    }
}
