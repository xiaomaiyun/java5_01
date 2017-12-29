package com.offcn.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.offcn.domain.FaceAccessToken;
import com.offcn.domain.FaceRegisterBean;
import com.offcn.enums.FaceConstant;
import com.offcn.service.FaceService;
import com.offcn.utils.HttpClientUtil;

@Service("faceService")
public class FaceServiceImpl implements FaceService {
	
	
	/**
	 * 发送httpGet请求
	 *  https://aip.baidubce.com/oauth/2.0/token?
	 * 				grant_type=client_credentials&
	 *				client_id=Va5yQRHlA4Fq4eR3LT0vuXV4&
	 * 				client_secret= 0rDSjzQ20XUj5itV7WRtznPQSzr5pVw2
	 */
	@Override
	public FaceAccessToken getAccessToken(String client_id, String client_secret) {
		
		String url = FaceConstant.accessTokenUrl.getVal()
					+ "client_id=" + client_id
					+ "&client_secret=" + client_secret;
		
		return HttpClientUtil.doGet(FaceAccessToken.class, url);
	}

	
	/**
	 * HTTP方法：POST
	 * 请求URL： https://aip.baidubce.com/rest/2.0/face/v2/faceset/user/add
	 * Header如下：Content-Type  application/x-www-form-urlencoded
	 * URL参数：access_token 
	 * Body中放置请求参数：
	 * 上面两个封装在FaceRegisterBean   其他的固定
	 */
	@Override
	public FaceRegisterBean addFace(FaceRegisterBean faceRegisterBean) {
		
		String url = FaceConstant.registerUrl.getVal();
		
		url = url + "?access_token=" + faceRegisterBean.getAccess_token();
		
		faceRegisterBean.setAccess_token(null);
		
		String header = FaceConstant.Content_Type.getVal();
		
		Map<String, String> headerMap = new HashMap<String, String>();
		
		headerMap.put("Content-Type", header);
		
		return HttpClientUtil.doPost(url, faceRegisterBean, headerMap);
	}

}