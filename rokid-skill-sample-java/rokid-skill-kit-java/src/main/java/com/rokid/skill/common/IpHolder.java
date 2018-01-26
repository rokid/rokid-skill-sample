package com.rokid.skill.common;

/**
 * 获得本机的Ip地址
 * 
 * @author Bassam
 *
 */
public class IpHolder {

	private static String ip;
	static {
		ip = MachineUtils.getSelfIp();
	}

	private IpHolder() {
	}

	public static String getSelfIp() {
		return ip;
	}

	public static void main(String[] args) {
		System.out.println(IpHolder.getSelfIp());
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			IpHolder.getSelfIp();
		}
		System.out.println(IpHolder.getSelfIp());

		System.out.println(System.currentTimeMillis() - start);
	}
}
