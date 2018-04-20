package com.jt.common.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HttpClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientService.class);

    @Autowired(required=false)
    private CloseableHttpClient httpClient;

    @Autowired(required=false)
    private RequestConfig requestConfig;

    /**
     * 实现httpClient的get请求
     * url：localhost:8093/addUser？id=1&name=tom
     * 参数设定
     * url表示请求路径
     * map<String,String>params 进行数据的封装
     * charset 字符集的编码
     * return json串
     * @throws URISyntaxException 
     * @throws Exception 
     */
    public String doGet(String uri,Map<String,String>params,String charset) throws URISyntaxException {
    	//拼接参数格式
    	//1 判断是否有参数
    	URIBuilder uriBuiler=new URIBuilder(uri);
    	if(params!=null){
    		for (Map.Entry<String, String>entry : params.entrySet()) {
    			uriBuiler.setParameter(entry.getKey(), entry.getValue());
			}
    	}
    	uri=uriBuiler.build().toString();
    	//2 判断编码是否为null，没有默认utf-8
    	if(StringUtils.isEmpty(charset)){
    		charset="utf-8";
    	}
    	//3 发送请求
    	HttpGet httpGet=new HttpGet(uri);
    	httpGet.setConfig(requestConfig);
    	CloseableHttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpGet);
			//4 判断响应结果
			if(httpResponse.getStatusLine().getStatusCode()==200){
				String result=EntityUtils.toString(httpResponse.getEntity());
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    	
    }
    /*
     * 表示只有uri不需要参数
     */
    public String doGet(String uri) throws URISyntaxException{
    	return doGet(uri,null,null);
    }
    public String doGet(String uri,Map<String,String>param) throws URISyntaxException{
    	return doGet(uri,param,null);
    }
    public String doPost(String uri) throws URISyntaxException, UnsupportedEncodingException{
    	return doPost(uri,null,null);
    }
    public String doPost(String uri,Map<String,String> params) throws URISyntaxException, UnsupportedEncodingException{
    	return doPost(uri,params,null);
    }
    
 /*   public String doPost(String uri,Object obj,String charset) throws URISyntaxException{
    	//拼接参数格式
    	//1 判断是否有参数
    	
    	//2 判断编码是否为null，没有默认utf-8
    	if(StringUtils.isEmpty(charset)){
    		charset="utf-8";
    	}
    	//3 发送请求
		try {
			HttpPost httpPost=new HttpPost(uri);
			
			httpPost.setEntity(new StringEntity(new ObjectMapper().writeValueAsString(obj),charset));
//			httpPost.setHeader("Content-Type", "Content-Type: application/x-www-form-urlencoded; charset=UTF-8");
			httpPost.setConfig(requestConfig);
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			//4 判断响应结果
			if(httpResponse.getStatusLine().getStatusCode()==200){
				String result=EntityUtils.toString(httpResponse.getEntity());
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }*/
    
    
    //实现httpclient的post提交方式
    /**
     * 1 创建post请求的对象
     * 2 添加请求参数
     * 3 将需要传递的参数通过form表单的形式进行数据的封装
     * 4 发出http请求，获取响应的信息
     * 5 判断响应是否成功，之后响应数据
     * @throws UnsupportedEncodingException 
     */
    
    public String doPost(String uri,Map<String,String> params,String charset) throws UnsupportedEncodingException{
    	//1
    	HttpPost httpPost=new HttpPost(uri);
    	//2
    	httpPost.setConfig(requestConfig);
    	//3
    	if(params!=null){
    		if(StringUtils.isEmpty(charset)){
        		charset="utf-8";
        	}
    		List<NameValuePair> list=new ArrayList<>();
    		for(Map.Entry<String, String> entry:params.entrySet()){
    			list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
    		}
    		UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(list,charset);
    		httpPost.setEntity(formEntity);
    		CloseableHttpResponse httpResponse;
    		try {
    			httpResponse = httpClient.execute(httpPost);
    			if(httpResponse.getStatusLine().getStatusCode()==200){
    				String result=EntityUtils.toString(httpResponse.getEntity());
    				return result;
    			}
    			httpResponse.close();
    		} catch (ClientProtocolException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
				
    	}

		return null;
    	
    	
    }
	
}