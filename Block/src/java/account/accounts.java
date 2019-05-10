package account;

import addCar.AlterCar;
import addCar.item;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.*;

import java.util.Map;

import static Connected.AddConnect.ConnectedIp;

public class accounts extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String walletAddress = request.getParameter("walletAddress");
        String ipInfo = ConnectedIp.get(walletAddress);
        Cookie cookies[] = request.getCookies();
        boolean isLogin = false;
        String username = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                isLogin = true;
                break;
            }
        }
        if (isLogin) {
            HttpSession session = request.getSession();
            if (session.getAttribute(username) != null) {
                AlterCar alterCar = (AlterCar) session.getAttribute(username);
                Map<Integer, item> map = alterCar.getItem();
                JSONObject jsonObject1 = new JSONObject();
                long allSize=0;
                for (Map.Entry<Integer, item> entry : map.entrySet()) {
                    int key = entry.getKey();
                    item value = entry.getValue();
                    JSONObject jsonObject2 = new JSONObject();
                    jsonObject2.put("url", value.getUrl());
                    jsonObject2.put("name", value.getName());
                    jsonObject2.put("price", value.getPrice());
                    jsonObject2.put("count", value.getPrice());
                    allSize+=value.getPrice()*value.getPrice();
                    jsonObject1.put(String.valueOf(key), jsonObject2);
                }

                String json_info = jsonObject1.toJSONString();

            }

        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }
}
