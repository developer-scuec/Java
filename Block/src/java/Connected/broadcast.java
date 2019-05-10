package Connected;

import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import static Connected.AddConnect.ConnectedIp;

public class broadcast extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setCharacterEncoding("utf-8");
        String block=request.getParameter("blockInfo");
        JSONObject jsonObject=JSONObject.parseObject(block);
        Map<String,String> ipInfo=ConnectedIp;
        System.out.println(block);
        for (Map.Entry<String,String> entry:ipInfo.entrySet()) {
            String ipAddress=entry.getValue();
            sendPost("http://localhost:9090/broastcast","block="+jsonObject.get("block")+"&sign="+jsonObject.get("sign"));
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request,response);
    }
    public  String sendPost(String url, String param){
        String result = "";
        try{
            URL realUrl = new URL(url);
            //打开和URL之间的连接
            URLConnection conn =  realUrl.openConnection();
            //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输出流
            PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"utf-8"));
            //发送请求参数
            out.print(param);
            //flush输出流的缓冲
            out.flush();
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常" + e);
            e.printStackTrace();
        }
        return result;
    }

}
