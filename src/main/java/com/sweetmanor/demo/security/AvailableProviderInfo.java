package com.sweetmanor.demo.security;

import java.security.Provider;
import java.security.Security;
import java.util.Map;

/**
 * 当前可用Provider（安全提供者）信息
 * 
 * @version 1.0 2016-06-15
 * @author wenz
 */
public class AvailableProviderInfo {

	/*
	 * 所有加密算法
	 */
	public static void allProvider() {
		for (Provider p : Security.getProviders())
			for (Provider.Service s : p.getServices())
				System.out.println(s.getAlgorithm());
	}

	/*
	 * 信息摘要算法
	 */
	public static void messageDigest() {
		for (Provider p : Security.getProviders()) {
			for (Provider.Service s : p.getServices()) {
				if (s.getType().equals("MessageDigest")) {
					System.out.println(s.getAlgorithm());
				}
			}
		}
	}

	public static void main(String[] args) {
		for (Provider p : Security.getProviders()) {
			System.out.println(p);// 打印当前提供者的信息
			for (Map.Entry<Object, Object> entry : p.entrySet()) {
				System.out.println("\t" + entry.getKey());// 打印提供者的键值
			}
		}
	}

}
