package Connected;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import static Connected.AddConnect.ConnectedIp;

public class getConnected extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Writer writer=response.getWriter();
            writer.write(JSON.toJSONString(ConnectedIp));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

}
