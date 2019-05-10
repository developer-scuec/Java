package order;

import addCar.AlterCar;
import addCar.item;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.*;
import java.io.Writer;
import java.util.Map;
import static Connected.AddConnect.ConnectedIp;

public class getOrder extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpSession session = request.getSession();
            response.setCharacterEncoding("UTF-8");
            Cookie cookies[] = request.getCookies();
            Writer out = response.getWriter();
            String username = null;
            boolean isLogin = false;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    isLogin = true;
                    break;
                }
            }
            if (!isLogin) {
                out.write("请先登录账号");
            } else {
                String walletAddress = request.getParameter("walletAddress");
                String IpAddress = ConnectedIp.get(walletAddress);
                AlterCar alterCar = (AlterCar) session.getAttribute(username);
                Map<Integer, item> map=alterCar.getItem();
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("IpAddress",IpAddress);
                jsonObject.put("orderInfo",map);
                out.write(jsonObject.toJSONString());
            }
            //out.write("添加购物车成功");


        } catch (Exception e) {

        }


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }
}
