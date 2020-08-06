package util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016102600763915";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCSSnZOMgqlSSjsOiq4UcdKbZcxAfwexZs9LrO1kVcZHEBmegw7DdDfpgGJ1VdpNj2vEN2KvrTp/8gVHLT/1aLa2sv/q0gAzeASqQOuIhIi2ey8MQMvexmSWwhKUYnmx7hQal1HGGfuIU2t3d8D+tqCuD7g/YlOSkD+CPMy83kikWW8wqjDwDLJROqy6eZOYM+ncFHAoaLnWcSMBrgsTTGnghHAJh/fSofy/h9G0Ndo2ZoGfBWORPASc1CgXfvTFMwRqN8+g3+5fobLOOHUlue1p8igJ0QFdL8kKGlaMpa5OV7TsRS9R5ojIErTzeZH1Pzb+tP7d1Xrq2RkNcQc+fm7AgMBAAECggEAeIkwB2gGC4BHXrTipDKFoZbAv5miEfzB11yWqCEUAdWcXpNrZnEQzGYs2FPXjRIrKwcbc4mE6VVel76qiC2P3QHwmj2Xu0nmuZlXNfBOvzaOb9V2iXXFcSjeoNWkaWngPSoWp1C9uaDpLzwj2FSmHJURAjMFImPePzJCqJWiFs8xzqlmcGQBcVNTTmxyoGu/dvQNK+PfLNbys7dn2OhpKpSUO5ZE05vi/R3jzOJOCK5t6f7Q9RIYRU3E20WH4n1Gy2tSJdkO2jQQlxjKP8VRkIbsPcyFcM20xplLJ4v0PXIaqBjHJFiUCz9dO8jFC9CTeJEPI0ro1Pt2ODaC2dCIqQKBgQDhNqeVHZiqWpOFTrZw5a5qQbvv62ZlnXuVuNK25ic1oZUc4DQDwdkPLwQ2Og2UJHASprpszBs17HAOfV95dBpkO/0eWSPpRiOMnoaHfkshN4SWYnmoo8etlAowmzKGWp25cmLJJiGMxgx2rGeb2ge/W3qvJJqeTkEwd/2TgHWKdQKBgQCmSekVkLlJJ60rQrQXXtpmVSgcJJ3Km/lGMHzoIcVRkRMrIzTMDqYC8ko5oXtm1AiOnHzeuU1ZHbYa98BRfjNSmODzz8rLISPsSH/2Ak89Q/Nz1OSK5DMpAnij0Ybid4sJ1SLuBlw+JcTeMbwMYz2ujkoDt9yWsfIsbws/HK8NbwKBgQDZFhnLfs/dxZlVcl4hzQOmoN5LkZkOchLia1mRu6An7i0RvHvm80ALhsKEksjr94OgNt6UaNoBzHrnPKp/JA8cd0axrjMTdOxna5c5m99HkYBYhWvHxrd+9ags2iyFnWtTipL2Wa8VRwJUkjL849l79u33TU1NMxlbYw4m1Dh40QKBgBmU0TiyFO7+HZPEJTRbMToA67S4pVWf/Q8hMnX7jAFqUgqW1e52am+VmnmXMAa3o8FcrZEN7KpC+drYNkvTIE4amrCnTMlHBeG5nYXZARupsnAlVitPw9cRPEHnX9rDDS6sDT2U2Ofuo4STHhzBv6F03koIJPVB1sGjK5W/8eYdAoGAUYmJBlUem492mpZcOni7xa/EP5P8HPDZ5MEMReclhe4WvFUC0E6CCuK/M2FuTK8rKZFYFOmJTpQogWstluXlFOXK8BVgRrP2WsPMadBcX0dUk0mfcL+c0g6ZSwO0ZeVofXvpCo0TCoCBkpIXip8RKyRIsA3vicFOLxH1WP6oPtc=";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgKddxfbnCx//Y/XMo3NdiYEWZD7/l9cC48InqUZiy8JpoHlB1GC4nuX+VL2EiavzFMzGXzNRhEgHi/Y5HCJyPB74zJ8p0+pOaghBbyZKqc5pYU0E73iFNCWMb9fx3rNj8zCQ3XZqAQer13RBTaeJBu1r5HQvnPIUnRvmjVyhE5OujoBMCMjXYi4SefJDDEGscDK2C50k0ZVH7USC7ALFMrl2vyHfPQ7qewDeDmDzN2jzFDFzdMl9zBcG8eQ+xgwODwKGYrdZbI5iGNihTs83eDkbz14LilAfNddDmV7avU33/VgqzxAAgdqCQu8QJQpZfIOwG2uqHWRjmh4JZyNX9QIDAQAB";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://47.106.177.147:8080/C85-S2-TTSX/user_center_order.html";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}

