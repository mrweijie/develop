/*  · Author by  zhongweijie
 *      Date on 2018/05/11
 * */
package cn.controller;

import cn.entity.Cake;
import cn.entity.Parameters;
import cn.service.BookService;
import cn.util.TestReadAndWrite;
import cn.util.Tools;
import cn.weixin2.sdk.WXPay;
import cn.weixin2.sdk.WXPayment;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;


@Controller
@RequestMapping(value = "/home")
public class HomeController {

	@Resource
	BookService bookService;

	@ResponseBody
	@RequestMapping(value="/login")
	public ModelAndView login(String username, String pwd) {
		System.out.println("用户名和密码：====="+ username +"-----"+ pwd);
		if(username != null && !username.equals("") && pwd != null && !"".equals(pwd)){

		}
		ModelAndView modelAndView = new ModelAndView("redirect:/home/index");
		return modelAndView;
	}

	@RequestMapping(value="/houtai")
	public String login() {
		return "login";
	}

	@RequestMapping(value="/index")
	public String index() {
		System.out.println("------------");
		String a ="a";
		String b ="b";
		a.compareTo(b);
		return "projectDetails";
	}


	@ResponseBody
	@RequestMapping(value="/list", method = {RequestMethod.GET} , produces = "application/json;charset=UTF-8")
	public String list(HttpServletRequest request){
		System.out.println(request.getParameter("name"));

		List<Cake> cakes = new ArrayList<Cake>();
		Cake cake1 = new Cake("草莓蛋糕",20,"草莓口味");
		Cake cake2 = new Cake("芒果蛋糕",20,"芒果口味");
		Cake cake3 = new Cake("榴莲蛋糕",20,"榴莲口味");
		Cake cake4 = new Cake("西瓜蛋糕",20,"西瓜口味");

		cakes.add(cake1);
		cakes.add(cake2);
		cakes.add(cake3);
		cakes.add(cake4);

		return Tools.toJson(cakes);
	}

	@RequestMapping(value="/tolist")
	public String tolist() {
		return "list";
	}

	@RequestMapping(value="/upload",method={RequestMethod.GET,RequestMethod.POST})
	public void batchimport(@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println("jinlaile ");
		TestReadAndWrite testReadAndWrite =new TestReadAndWrite();
		testReadAndWrite.read(file);
	}

	//微信小程序支付接口
	@ResponseBody
	@RequestMapping(value="/pay", method = {RequestMethod.GET} , produces = "application/json;charset=UTF-8")
	public String pay(HttpServletRequest request){
		Parameters paramter = new Parameters();
		paramter.out_trade_no = request.getParameter("ordercode");

		WXPayment wxPayment = new WXPayment(request.getParameter("appid"),request.getParameter("mchId"),request.getParameter("key"));
		try {
			WXPay wxPay =new WXPay(wxPayment);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

}
