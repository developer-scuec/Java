package Connected;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

public class AddConnect extends HttpServlet {
    public static Map<String,String> ConnectedIp =new LinkedHashMap<>();
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String WalletAddress=request.getParameter("WalletAddress");
        String IpAddress=request.getParameter("IpAddress");
        ConnectedIp.put(WalletAddress,IpAddress);
        try {
            Writer writer=response.getWriter();
            writer.write(JSON.toJSONString(ConnectedIp));
            System.out.println(JSON.toJSONString(ConnectedIp));
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request,response);
    }

}
