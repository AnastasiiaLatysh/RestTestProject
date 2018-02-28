package main.test;

import main.controllers.TestData;
import org.junit.After;
import org.junit.Test;

public class DeleteUserTests extends TestBase {

    @Test
    public void testDeleteContact() {
		/*`
		 * Performs DELETE operation on http://<host>:<port>/<endpoint>/<user_id>
		 * and delete information of client
		 * and check correctness of returned information
		 * then try to get deleted user
		 */

		int userId = executor.getLastAddedUserId(TestData.generalEndPoint);
        executor.delete(TestData.generalEndPoint + "/" + userId)
                .expectCode(200)            // Expected code of 200
                .expectMessage(TestData.statusCodeMessages.get("200"))        // Expected Message
                .expectHeader(TestData.contentTypeKey, TestData.contentType) // Content-Type header value
                .expectInBody(String.valueOf(executor.getLastAddedUserId(
                        TestData.generalEndPoint))) // Response body contains user id
                .expectInBody(TestData.userData.get("userEmail")) // Response body contains user email
                .expectInBody(TestData.userData.get("userName")) // Response body contains user name
                .expectInBody(TestData.userData.get("userLastName")); // Response body contains user last name

        executor.get(TestData.generalEndPoint + "/" + userId)
                .expectCode(404)            // Expected code of 404
                .expectMessage(TestData.statusCodeMessages.get("404"));        // Expected Message
    }

    @After
    public void deleteUsers(){}
}
