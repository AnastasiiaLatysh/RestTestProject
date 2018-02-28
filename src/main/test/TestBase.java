package main.test;

import main.controllers.RestExecutor;
import main.controllers.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestBase {

    public static final String URL = TestData.URL;
    public static RestExecutor executor;

    @BeforeClass
    public static void setUp() {
		/*
		 * Initialize RestExecutor object using the end point URL
		 */
        executor = new RestExecutor(URL);
    }

    @Before
    public void addUser(){
        executor.post(TestData.generalEndPoint, TestData.userJsonData.get("jsonContent"), TestData.contentType);
    }

    @After
    public void deleteUsers(){
        executor.delete(TestData.generalEndPoint + "/" + executor.getLastAddedUserId(TestData.generalEndPoint));
    }
}
