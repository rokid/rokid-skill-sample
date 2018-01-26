package com.rokid.skill.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.apache.commons.lang.StringUtils;

/**
 * 服务器设备信息工具类
 * 
 * @author Bassam
 *
 */
public class MachineUtils {
	// 这个方法很耗时，一定要cache住
	public static String getSelfIp() {
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			if (allNetInterfaces != null) {
				while (allNetInterfaces.hasMoreElements()) {
					NetworkInterface netInterface = allNetInterfaces.nextElement();
					Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
					if (addresses != null) {
						while (addresses.hasMoreElements()) {
							ip = addresses.nextElement();
							if (ip != null && ip instanceof Inet4Address) {
								// System.out.println(ip.getHostAddress());
								if (!StringUtils.equals("127.0.0.1", ip.getHostAddress())) {
									return ip.getHostAddress();
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取服务器唯一标识
	 * 
	 * @return
	 */
	public static String geDeviceId() {
		String localMac = null;
		try {
			localMac = getLocalMac();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (StringUtils.isBlank(localMac)) {
			localMac = getUnixMACAddress();
		}
		if (StringUtils.isBlank(localMac)) {
			return null;
		}
		try {
			String deviceId = MD5Utils.getMD5String(localMac);
			return deviceId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取本地Mac地址
	 * 
	 * @return
	 * @throws SocketException
	 */
	private static String getLocalMac() throws SocketException {
		String locaMac = "";
		InetAddress ia;
		try {
			ia = InetAddress.getLocalHost();
			// 获取网卡，获取地址
			byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
			if (mac == null) {
				return locaMac;
			}

			// System.out.println("mac数组长度：" + mac.length);
			StringBuffer sb = new StringBuffer("");
			for (int i = 0; i < mac.length; i++) {
				if (i != 0) {
					sb.append("-");
				}
				// 字节转换为整数
				int temp = mac[i] & 0xff;
				String str = Integer.toHexString(temp);
				// System.out.println("每8位:" + str);
				if (str.length() == 1) {
					sb.append("0" + str);
				} else {
					sb.append(str);
				}
			}
			locaMac = sb.toString().toUpperCase();
			// System.out.println("本机MAC地址:" + locaMac);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return locaMac;
	}

	/**
	 * 获取unix网卡的mac地址. 非windows的系统默认调用本方法获取. 如果有特殊系统请继续扩充新的取mac地址方法.
	 * 
	 * @return mac地址
	 */
	private static String getUnixMACAddress() {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			// linux下的命令，一般取eth0作为本地主网卡
			process = Runtime.getRuntime().exec("ifconfig eth0");
			// 显示信息中包含有mac地址信息
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				// 寻找标示字符串[hwaddr]
				index = line.toLowerCase().indexOf("hwaddr");
				if (index >= 0) {// 找到了
					// 取出mac地址并去除2边空格
					mac = line.substring(index + "hwaddr".length() + 1).trim();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}
		return mac;
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1; i++) {
			MachineUtils.getSelfIp();
		}
		System.out.println(System.currentTimeMillis() - start);
	}
}
