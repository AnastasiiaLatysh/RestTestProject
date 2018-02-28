package main.test;

import main.controllers.TestData;
import org.junit.After;
import org.junit.Test;


public class UpdateUserTests extends TestBase {

    @Test
    public void testUpdateContact() {
		/*`
		 * Performs PUT operation on http://<host>:<port>/<endpoint>/<user_id>
		 * and update information of client
		 * then check if user information was updated
		 * Note: before PUT method new user is added, after GET method this user will be deleted
		 */
        executor.put(TestData.generalEndPoint + "/" + executor.getLastAddedUserId(TestData.generalEndPoint),
                TestData.userJsonData.get("jsonContentUpdated"), TestData.contentType)
                .expectCode(200)            // Expected code of 200
                .expectMessage(TestData.statusCodeMessages.get("200"))        // Expected Message
                .expectHeader(TestData.contentTypeKey, TestData.contentType) // Content-Type header value
                .expectInBody(TestData.userJsonData.get("jsonContentUpdated"));  // Content inside the response body

        executor.get(TestData.generalEndPoint + "/" + executor.getLastAddedUserId(TestData.generalEndPoint))
                .expectCode(200)            // Expected code of 200
                .expectInBody(TestData.userData.get("updatedEmail")) // Response body contains updated user email
                .expectInBody(TestData.userData.get("updatedName")) // Response body contains updated user name
                .expectInBody(TestData.userData.get("updatedLastName")); // Response body contains updated user last name
    }

    @Test
    public void testPartialUpdateContact() {
		/*`
		 * Performs PATCH operation on http://<host>:<port>/<endpoint>/<user_id>
		 * and update information of client
		 * then check if user information was updated
		 * Note: before PATCH method new user is added, after GET method this user will be deleted
		 */
        executor.patch(TestData.generalEndPoint + "/" + executor.getLastAddedUserId(TestData.generalEndPoint),
                TestData.userJsonData.get("jsonContentPartialUpdated"), TestData.contentType)
                .expectCode(200)            // Expected code of 200
                .expectInBody(TestData.userData.get("userEmail")) // Response body contains previous user email
                .expectInBody(TestData.userData.get("updatedName")) // Response body contains updated user name
                .expectInBody(TestData.userData.get("userLastName")); // Response body contains previous user last name
    }

    @After
    public void deleteUsers(){}
}
