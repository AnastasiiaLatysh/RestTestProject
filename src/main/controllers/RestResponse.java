package main.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.util.HashMap;

public class RestResponse {

	private int responseCode;
	private String responseBody;
	private HashMap<String, String> headers;
	private String responseMessage;

	public RestResponse() {
		headers = new HashMap<String, String>();
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public String getHeader(String name) {
		return headers.get(name);
	}

	public void setHeader(String name, String value) {
		this.headers.put(name, value);
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public int getNumberOfUsers() {
		return new JsonParser().parse(responseBody).getAsJsonObject().get("data").getAsJsonArray().size();
	}
	public int getIdOfLastAddedUser() {
		if (this.getNumberOfUsers() > 0)
		{
			JsonArray usersData = new JsonParser().parse(responseBody).getAsJsonObject().get("data").
					getAsJsonArray();
			return  Integer.parseInt(String.valueOf(usersData.get(usersData.size() - 1).getAsJsonObject().get("id")));
		}
		else {
			throw new NullPointerException("There are no users");
		}
	}
}
