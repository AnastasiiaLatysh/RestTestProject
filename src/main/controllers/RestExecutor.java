package main.controllers;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class RestExecutor {

	private HttpClient client;
	private String url;

	/**
	 * Constructor for RestExecutor
	 *
	 * @param url
	 */
	public RestExecutor(String url) {
		client = HttpClientBuilder.create().build();
		this.url = url;
	}

	public RestValidator get(String path) {
		return get(path, null);
	}

	/**
	 * Executes GET req and returns response json.
	 *
	 * @param path
	 * @param headers
	 * @return
	 */

	public RestValidator get(String path, HashMap<String, String> headers) {
		HttpGet request = new HttpGet(url + path);
		HttpResponse response;
		RestResponse restResponse = new RestResponse();
		try {
			/*
			 * Setting the headers for the request
			 */
			if (headers != null) {
				Set<String> keys = headers.keySet();
				for (String key : keys) {
					request.addHeader(key, headers.get(key));
				}
			}
			/* 
			 * Executing the GET operation
			 */
			response = client.execute(request);
			
			/*
			 * Obtaining the response body from the response stream.
			 */
			restResponse = ResponseInfo.getRestResponseInfo(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * Returns the RestValidator object providing the response object
		 */
		return new RestValidator(restResponse);
	}

	public RestValidator post(String path, String jsonContent, String contentType) {
		return post(path, null, jsonContent, contentType);
	}

	/**
	 * Executes POST req and returns response json.
	 *
	 * @param path
	 * @param headers
	 * @return
	 */
	public RestValidator post(String path, HashMap<String, String> headers, String jsonContent, String contentType) {
		HttpPost post = new HttpPost(url + path);
		RestResponse restResponse = new RestResponse();
		try {
			if (headers != null)
				post.setEntity(getEntities(headers));

			/*
			 * Setting the json content and content type.
			 */
			StringEntity input = new StringEntity(jsonContent);
			input.setContentType(contentType);
			post.setEntity(input);

			HttpResponse response = client.execute(post);
			restResponse = ResponseInfo.getRestResponseInfo(response);
		} catch (Exception e) {
			e.printStackTrace(); // handle
		}
		return new RestValidator(restResponse);
	}

	public RestValidator delete(String path) {
		return delete(path, null);
	}

	/**
	 * Executes DELETE req and returns response json.
	 *
	 * @param path
	 * @param headers
	 * @return
	 */
	public RestValidator delete(String path, HashMap<String, String> headers) {
		HttpDelete delete = new HttpDelete(url + path);
		RestResponse restResponse = new RestResponse();
		try {
			if (headers != null) {
				Set<String> keys = headers.keySet();
				for (String key : keys) {
					delete.addHeader(key, headers.get(key));
				}
			}
			HttpResponse response = client.execute(delete);
			restResponse = ResponseInfo.getRestResponseInfo(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new RestValidator(restResponse);
	}

	public RestValidator put(String path, String jsonContent, String contentType) {
		return put(path, null, jsonContent, contentType);
	}

	/**
	 * Executes PUT req and returns response json.
	 *
	 * @param path
	 * @param headers
	 * @param jsonContent
	 * @param contentType
	 * @return
	 */
	public RestValidator put(String path, HashMap<String, String> headers, String jsonContent, String contentType) {
		HttpPut put = new HttpPut(url + path);
		RestResponse restResponse = new RestResponse();
		try {
			if (headers != null)
				put.setEntity(getEntities(headers));

			StringEntity input = new StringEntity(jsonContent);
			input.setContentType(contentType);
			put.setEntity(input);

			HttpResponse response = client.execute(put);
			restResponse = ResponseInfo.getRestResponseInfo(response);
		} catch (Exception e) {
			e.printStackTrace(); // handle
		}
		return new RestValidator(restResponse);
	}

	public RestValidator patch(String path, String jsonContent, String contentType) {
		return patch(path, null, jsonContent, contentType);
	}

	/**
	 * Executes PATCH req and returns response json.
	 *
	 * @param path
	 * @param headers
	 * @param jsonContent
	 * @param contentType
	 * @return
	 */
	public RestValidator patch(String path, HashMap<String, String> headers, String jsonContent, String contentType) {
		HttpPatch post = new HttpPatch(url + path);
		RestResponse restResponse = new RestResponse();
		try {
			if (headers != null)
				post.setEntity(getEntities(headers));

			StringEntity input = new StringEntity(jsonContent);
			input.setContentType(contentType);
			post.setEntity(input);

			HttpResponse response = client.execute(post);
			restResponse = ResponseInfo.getRestResponseInfo(response);
		} catch (Exception e) {
			e.printStackTrace(); // handle
		}
		return new RestValidator(restResponse);
	}

	/**
	 * Gets the hashmap turns it in HttpEntity nameValuePair.
	 *
	 * @param inputEntities
	 * @return
	 */
	private HttpEntity getEntities(HashMap<String, String> inputEntities) {
		List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>(inputEntities.size());
		Set<String> keys = inputEntities.keySet();
		for (String key : keys) {
			nameValuePairs.add(new BasicNameValuePair(key, inputEntities.get(key)));
		}
		try {
			return new UrlEncodedFormEntity(nameValuePairs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getLastAddedUserId(String url) {
		return this.get(url).getResponse().getIdOfLastAddedUser();
	}

	public boolean isUsersExist(String url) {
		return this.get(url).getResponse().getNumberOfUsers() > 0;
	}
}
