package main.test;

import main.controllers.TestData;
import org.junit.Test;

public class GetUserTests extends TestBase {

    @Test
    public void testGetAllContacts() {
		/*
		 * Performs GET operation on http://<host>:<port>/<endpoint>
		 * and check that returned json contains all necessary information about clients
		 * Note: before GET method new user is added, after GET method this user will be deleted
		 */
        executor.get(TestData.generalEndPoint)
                .expectCode(200)            // Expected code of 200
                .expectMessage(TestData.statusCodeMessages.get("200"))        // Expected Message of 'OK'
                .expectHeader(TestData.contentTypeKey, TestData.contentType) // Content-Type header value
                .expectInBody(TestData.userData.get("userEmail")) // Response body contains user email
                .expectInBody(TestData.userData.get("userName")) // Response body contains user name
                .expectInBody(TestData.userData.get("userLastName")); // Response body contains user last name
    }

    @Test
    public void testGetContactById() {
		/*
		 * Performs GET operation on http://<host>:<port>/<endpoint>/<user_id>
		 * and check that returned json contains all necessary information about client
		 * Note: before GET method new user is added, after GET method this user will be deleted
		 */
        executor.get(TestData.generalEndPoint + "/" + executor.getLastAddedUserId(TestData.generalEndPoint))
                .expectCode(200)            // Expected code of 200
                .expectMessage(TestData.statusCodeMessages.get("200"))        // Expected Message
                .expectHeader(TestData.contentTypeKey, TestData.contentType) // Content-Type header value
                .expectInBody(TestData.userData.get("userEmail")) // Response body contains user email
                .expectInBody(TestData.userData.get("userName")) // Response body contains user name
                .expectInBody(TestData.userData.get("userLastName")); // Response body contains user last name

    }

    @Test
    public void testGetContactByName() {
		/*
		 * Performs GET operation on http://<host>:<port>/<endpoint>/?firstName=<user_name>&email=<user_email>
		 * and check that returned json contains all necessary information about client
		 * Note: before GET method new user is added, after GET method this user will be deleted
		 */
        executor.get(TestData.generalEndPoint + "/?" + TestData.queryParams.get("firstName") +
                TestData.userData.get("userName") + "&" + TestData.queryParams.get("email") +
                TestData.userData.get("userEmail"))
                .expectCode(200)            // Expected code of 200
                .expectMessage(TestData.statusCodeMessages.get("200"))        // Expected Message
                .expectHeader(TestData.contentTypeKey, TestData.contentType) // Content-Type header value
                .expectInBody(TestData.userData.get("userEmail")) // Response body contains user email
                .expectInBody(TestData.userData.get("userName")) // Response body contains user name
                .expectInBody(TestData.userData.get("userLastName")); // Response body contains user last name
    }
}
