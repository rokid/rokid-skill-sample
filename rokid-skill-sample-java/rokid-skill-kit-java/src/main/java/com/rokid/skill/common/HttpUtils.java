package com.rokid.skill.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * Http请求工具类
 * 
 * @author Bassam
 *
 */
public class HttpUtils {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class.getName());

	/**
	 * http get 请求
	 * 
	 * @param requestId
	 *            请求ID
	 * @param url
	 *            请求地址
	 * @param bodyParam
	 *            请求参数
	 * @param connectTimeout
	 *            建立连接超时时间
	 * @param connectionTimeout
	 *            连接超时时间
	 * @param soTimeout
	 *            数据传输超时时间
	 * @return
	 */
	public static HttpResult sendGet(String requestId, String url, Map<String, String> bodyParam, int connectTimeout,
			int connectionTimeout, int soTimeout) {
		return sendGet(requestId, url, null, bodyParam, connectTimeout, connectionTimeout, soTimeout);
	}

	/**
	 * Http get请求
	 * 
	 * @param requestId
	 *            请求Id
	 * @param url
	 *            请求地址
	 * @param headerParam
	 *            请求头
	 * @param bodyParam
	 *            请求体
	 * @param connectTimeout
	 *            建立连接超时时间
	 * @param connectionTimeout
	 *            连接超时时间
	 * @param soTimeout
	 *            数据传输超时时间
	 * @return
	 */
	public static HttpResult sendGet(String requestId, String url, Map<String, String> headerParam,
			Map<String, String> bodyParam, int connectTimeout, int connectionTimeout, int soTimeout) {
		if (StringUtils.isBlank(requestId)) {
			requestId = System.currentTimeMillis() + "";
		}
		HttpResult result = new HttpResult();
		result.setHttpcode(HttpStatus.FORBIDDEN.value());
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(soTimeout)
				.setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionTimeout).build();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
		if (MapUtils.isNotEmpty(bodyParam)) {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			String param = "";
			for (Map.Entry<String, String> entry : bodyParam.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				if (StringUtils.isBlank(param)) {
					param += entry.getKey() + "=" + entry.getValue();
				} else {
					param += "&" + entry.getKey() + "=" + entry.getValue();
				}
			}
			url = url + param;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("RequestId:" + requestId + ";Url:" + url);
			if (headerParam != null) {
				logger.debug("RequestId:" + requestId + ";HeaderParam:" + headerParam.toString());
			}
		}
		try {
			HttpGet httpGet = new HttpGet(url);
			if (MapUtils.isNotEmpty(headerParam)) {
				for (Map.Entry<String, String> entry : headerParam.entrySet()) {
					httpGet.addHeader(entry.getKey(), entry.getValue());
				}
			}
			CloseableHttpResponse response = httpclient.execute(httpGet);
			if (response != null) {
				try {
					HttpEntity entity = response.getEntity();
					int httpcode = 0;
					if (response.getStatusLine() != null) {
						httpcode = response.getStatusLine().getStatusCode();
					}
					String httpResult = "";
					if (entity != null) {
						httpResult = EntityUtils.toString(entity, "UTF-8");
					}
					result.setHttpcode(httpcode);
					result.setResult(httpResult);
				} finally {
					response.close();
				}
			}
		} catch (ClientProtocolException e) {
			result.setErrorMessage(ExceptionUtils.getStackTrace(e));
			if (logger.isErrorEnabled()) {
				logger.error("ClientProtocolException RequestId:" + requestId, e);
			}
		} catch (UnsupportedEncodingException e1) {
			result.setErrorMessage(ExceptionUtils.getStackTrace(e1));
			if (logger.isErrorEnabled()) {
				logger.error("UnsupportedEncodingException RequestId:" + requestId, e1);
			}
		} catch (IOException e2) {
			result.setErrorMessage(ExceptionUtils.getStackTrace(e2));
			if (logger.isErrorEnabled()) {
				logger.error("IOException RequestId:" + requestId, e2);
			}
		} catch (Exception e3) {
			result.setErrorMessage(ExceptionUtils.getStackTrace(e3));
			if (logger.isErrorEnabled()) {
				logger.error("Exception RequestId:" + requestId, e3);
			}
		} finally {// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				// Ingore
				if (logger.isErrorEnabled()) {
					logger.error("IOException RequestId:" + requestId, e);
				}
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("RequestId:" + requestId + ";HttpResult:" + result.toString());
		}
		return result;
	}

	/**
	 * Http post请求 Key-Value方式
	 * 
	 * @param requestId
	 *            请求Id
	 * @param url
	 *            请求Url地址
	 * @param bodyParam
	 *            请求体
	 * @param connectTimeout
	 *            建立连接超时时间
	 * @param connectionTimeout
	 *            连接超时时间
	 * @param soTimeout
	 *            数据传输超时时间
	 * @return
	 */
	public static HttpResult sendPost(String requestId, String url, Map<String, String> bodyParam, int connectTimeout,
			int connectionTimeout, int soTimeout) {
		return sendPost(requestId, url, null, bodyParam, connectTimeout, connectionTimeout, soTimeout);
	}

	/**
	 * Http post请求 Key-Value方式
	 * 
	 * @param requestId
	 * @param url
	 *            请求地址
	 * @param headerParam
	 *            请求头
	 * @param bodyParam
	 *            请求体
	 * @param connectTimeout
	 *            设置连接超时时间，单位毫秒。
	 * @param connectionTimeout
	 *            设置从connect Manager获取Connection
	 *            超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
	 * @param soTimeout
	 *            请求获取数据的超时时间，单位毫秒。
	 * @return
	 */
	public static HttpResult sendPost(String requestId, String url, Map<String, String> headerParam,
			Map<String, String> bodyParam, int connectTimeout, int connectionTimeout, int soTimeout) {
		if (StringUtils.isBlank(requestId)) {
			requestId = System.currentTimeMillis() + "";
		}
		HttpResult result = new HttpResult();
		result.setHttpcode(HttpStatus.FORBIDDEN.value());
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(soTimeout)
				.setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionTimeout).build();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
		if (logger.isDebugEnabled()) {
			logger.debug("RequestId:" + requestId + ";Url:" + url);
			if (headerParam != null) {
				logger.debug("RequestId:" + requestId + ";HeaderParam:" + headerParam.toString());
			}
			if (bodyParam != null) {
				logger.debug("RequestId:" + requestId + ";BodyParam:" + bodyParam.toString());
			}
		}
		try {
			HttpPost httppost = new HttpPost(url);
			if (MapUtils.isNotEmpty(headerParam)) {
				for (Map.Entry<String, String> entry : headerParam.entrySet()) {
					httppost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			if (MapUtils.isNotEmpty(bodyParam)) {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				String param = "";
				for (Map.Entry<String, String> entry : bodyParam.entrySet()) {
					params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
					if (StringUtils.isBlank(param)) {
						param += entry.getKey() + "=" + entry.getValue();
					} else {
						param += "&" + entry.getKey() + "=" + entry.getValue();
					}
				}
				StringEntity entity = new StringEntity(param);
				if (headerParam == null || headerParam.get("Content-Type") == null) {
					entity.setContentType("application/x-www-form-urlencoded");
				}
				httppost.setEntity(entity);
			}
			CloseableHttpResponse response = httpclient.execute(httppost);
			if (response != null) {
				try {
					HttpEntity entity = response.getEntity();
					int httpcode = 0;
					if (response.getStatusLine() != null) {
						httpcode = response.getStatusLine().getStatusCode();
					}
					String httpResult = "";
					if (entity != null) {
						httpResult = EntityUtils.toString(entity, "UTF-8");
					}
					result.setHttpcode(httpcode);
					result.setResult(httpResult);
				} finally {
					response.close();
				}
			}
		} catch (ClientProtocolException e) {
			result.setErrorMessage(ExceptionUtils.getStackTrace(e));
			if (logger.isErrorEnabled()) {
				logger.error("ClientProtocolException RequestId:" + requestId, e);
			}
		} catch (UnsupportedEncodingException e1) {
			result.setErrorMessage(ExceptionUtils.getStackTrace(e1));
			if (logger.isErrorEnabled()) {
				logger.error("UnsupportedEncodingException RequestId:" + requestId, e1);
			}
		} catch (IOException e2) {
			result.setErrorMessage(ExceptionUtils.getStackTrace(e2));
			if (logger.isErrorEnabled()) {
				logger.error("IOException RequestId:" + requestId, e2);
			}
		} catch (Exception e3) {
			result.setErrorMessage(ExceptionUtils.getStackTrace(e3));
			if (logger.isErrorEnabled()) {
				logger.error("Exception RequestId:" + requestId, e3);
			}
		} finally {// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				// Ingore
				if (logger.isErrorEnabled()) {
					logger.error("IOException RequestId:" + requestId, e);
				}
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("RequestId:" + requestId + ";HttpResult:" + result.toString());
		}
		return result;
	}

	/**
	 * Http Post raw方式请求
	 * 
	 * @param requestId
	 *            请求Id
	 * @param url
	 *            请求地址
	 * @param bodyParam
	 *            请求体
	 * @param connectTimeout
	 *            设置连接超时时间，单位毫秒。
	 * @param connectionTimeout
	 *            设置从connect Manager获取Connection
	 *            超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
	 * @param soTimeout
	 *            请求获取数据的超时时间，单位毫秒。
	 * @return
	 */
	public static HttpResult sendPost(String requestId, String url, String bodyParam, int connectTimeout,
			int connectionTimeout, int soTimeout) {
		return sendPost(requestId, url, null, bodyParam, connectTimeout, connectionTimeout, soTimeout);
	}

	/**
	 * Http Post raw方式请求
	 * 
	 * @param requestId
	 *            请求Id
	 * @param url
	 *            请求地址
	 * @param headerParam
	 *            请求头
	 * @param bodyParam
	 *            请求体
	 * @param connectTimeout
	 *            设置连接超时时间，单位毫秒。
	 * @param connectionTimeout
	 *            设置从connect Manager获取Connection
	 *            超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
	 * @param soTimeout
	 *            请求获取数据的超时时间，单位毫秒。
	 * @return
	 */
	public static HttpResult sendPost(String requestId, String url, Map<String, String> headerParam, String bodyParam,
			int connectTimeout, int connectionTimeout, int soTimeout) {
		if (StringUtils.isBlank(requestId)) {
			requestId = System.currentTimeMillis() + "";
		}
		HttpResult result = new HttpResult();
		result.setHttpcode(HttpStatus.FORBIDDEN.value());
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(soTimeout)
				.setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionTimeout).build();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
		if (logger.isDebugEnabled()) {
			logger.debug("RequestId:" + requestId + ";Url:" + url);
			if (headerParam != null) {
				logger.debug("RequestId:" + requestId + ";HeaderParam:" + headerParam.toString());
			}
			logger.debug("RequestId:" + requestId + ";BodyParam:" + bodyParam);
		}
		try {
			HttpPost httppost = new HttpPost(url);
			if (MapUtils.isNotEmpty(headerParam)) {
				for (Map.Entry<String, String> entry : headerParam.entrySet()) {
					httppost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			StringEntity stringEntity = new StringEntity(bodyParam);
			// 设置类型
			stringEntity.setContentType("application/json");
			httppost.setEntity(stringEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			if (response != null) {
				try {
					HttpEntity entity = response.getEntity();
					int httpcode = 0;
					if (response.getStatusLine() != null) {
						httpcode = response.getStatusLine().getStatusCode();
					}
					String httpResult = "";
					if (entity != null) {
						httpResult = EntityUtils.toString(entity, "UTF-8");
					}
					result.setHttpcode(httpcode);
					result.setResult(httpResult);
				} finally {
					response.close();
				}
			}
		} catch (ClientProtocolException e) {
			result.setErrorMessage(ExceptionUtils.getStackTrace(e));
			if (logger.isErrorEnabled()) {
				logger.error("ClientProtocolException RequestId:" + requestId, e);
			}
		} catch (UnsupportedEncodingException e1) {
			result.setErrorMessage(ExceptionUtils.getStackTrace(e1));
			if (logger.isErrorEnabled()) {
				logger.error("UnsupportedEncodingException RequestId:" + requestId, e1);
			}
		} catch (IOException e2) {
			result.setErrorMessage(ExceptionUtils.getStackTrace(e2));
			if (logger.isErrorEnabled()) {
				logger.error("IOException RequestId:" + requestId, e2);
			}
		} catch (Exception e3) {
			result.setErrorMessage(ExceptionUtils.getStackTrace(e3));
			if (logger.isErrorEnabled()) {
				logger.error("Exception RequestId:" + requestId, e3);
			}
		} finally {// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				// Ingore
				if (logger.isErrorEnabled()) {
					logger.error("IOException RequestId:" + requestId, e);
				}
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("RequestId:" + requestId + ";HttpResult:" + result.toString());
		}
		return result;
	}

	public static void main(String[] args) {

	}
}
