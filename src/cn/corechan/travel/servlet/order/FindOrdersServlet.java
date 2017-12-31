package cn.corechan.travel.servlet.order;

import cn.corechan.travel.dao.proxy.OrderDAOProxy;
import cn.corechan.travel.json.Status;
import cn.corechan.travel.json.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class FindOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("UTF-8");
        String phoneNumber= (String) req.getSession().getAttribute("phoneNumber");
        if(phoneNumber==null){
            ResponseUtil.ResponseUnlogin(resp);
            return;
        }
        try {
            OrderDAOProxy orderDAOProxy=new OrderDAOProxy();
            Status status=orderDAOProxy.FindOrders(phoneNumber);
            ResponseUtil.Render(resp,status);
        } catch (SQLException | ClassNotFoundException e) {
            ResponseUtil.ResponseError(resp,e);
        }
    }
}
