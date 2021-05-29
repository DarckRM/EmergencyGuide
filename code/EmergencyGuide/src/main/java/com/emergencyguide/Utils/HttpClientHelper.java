package com.emergencyguide.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.List;

public class HttpClientHelper {
	/**
	 * 模拟HttpGet 请求
	 * 
	 * @param url
	 * @return
	 */
	public static String HttpGet(String url) {
		// 单位毫秒
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(3000).setConnectTimeout(3000)
				.setSocketTimeout(3000).build();// 设置请求的状态参数
		CloseableHttpClient httpclient = HttpClients.createDefault();// 创建 CloseableHttpClient
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpGet);// 返回请求执行结果
			int statusCode = response.getStatusLine().getStatusCode();// 获取返回的状态值
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			} else {
				String result = EntityUtils.toString(response.getEntity(), "UTF-8");
				return result;
			}
		} catch (Exception e) {
			// logger.error("httpget Exception handle-- > " + e);
		} finally {
			if (response != null) {
				try {
					response.close();// 关闭response
				} catch (IOException e) {
					// logger.error("httpget IOException handle-- > " + e);
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();// 关闭httpclient
				} catch (IOException e) {
					// logger.error("httpget IOException handle-- > " + e);
				}
			}
		}
		return null;
	}
	/**
	 * post请求（用于请求json格式的参数）
	 *
	 * @param url
	 *            地址
	 * @param params
	 *            json格式的参数
	 * @return
	 */
	public static String doPost(String url, String params) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);// 创建httpPost
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Connection", "Keep-Alive");
		//String authorization = WeChatUtils.genAuthorization("POST",url.replace("https://api.mch.weixin.qq.com/",""),config);
		//httpPost.setHeader("Authorization", "WECHATPAY2-SHA256-RSA2048 "+authorization);
		String charSet = "UTF-8";
		StringEntity entity = new StringEntity(params, charSet);
		httpPost.setEntity(entity);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
			org.apache.http.StatusLine status = response.getStatusLine();
			int state = status.getStatusCode();
			if (state == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				String jsonString = EntityUtils.toString(responseEntity);
				System.err.println("请求支付成功返回:" + jsonString);
				return jsonString;
			} else {
				System.err.println("请求失败返回:" + state + "(" + url + ")");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception ee){

		}finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * post请求（用于请求二维码生成）
	 *
	 * @param url
	 *            地址
	 * @param params
	 *            json格式的参数
	 * @return
	 */
	public static Object doPostForCode(String url, String params) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);// 创建httpPost
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Connection", "Keep-Alive");
		String charSet = "UTF-8";
		StringEntity entity = new StringEntity(params, charSet);
		httpPost.setEntity(entity);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
			org.apache.http.StatusLine status = response.getStatusLine();
			int state = status.getStatusCode();
			if (state == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				if(responseEntity.getContentType().getValue().contains("image")){
					InputStream inputStream = responseEntity.getContent();
					byte[] data = readInputStream(inputStream);
					return data;
				}
				String jsonString = EntityUtils.toString(responseEntity);
				return jsonString;
			} else {
				System.err.println("请求失败返回:" + state + "(" + url + ")");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception ee){

		}finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	private static byte[] readInputStream(InputStream inputStream) throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		//创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		//每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		//使用一个输入流从buffer里把数据读取出来
		while ((len = inputStream.read(buffer)) != -1) {
			//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		//把outStream里的数据写入内存
		return outStream.toByteArray();
	}

	public static void main1(String[] args) {

	}
}
