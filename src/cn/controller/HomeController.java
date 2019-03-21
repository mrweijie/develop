/*  · Author by  zhongweijie
 *      Date on 2018/05/11
 * */
package cn.controller;

import cn.entity.Cake;
import cn.service.BookService;
import cn.util.TestReadAndWrite;
import cn.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;


@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/home")
public class HomeController {

	@Autowired
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
		return "show";
	}

	@RequestMapping(value="/details")
	public String projectDetails() {
		return "projectDetails";
	}


	@ResponseBody
	@RequestMapping(value="/list", method = {RequestMethod.GET} , produces = "application/json;charset=UTF-8")
	public String list(HttpServletRequest request){
		System.out.println(request.getParameter("name"));

		List<Cake> cakes = new ArrayList<Cake>();
		String[] alist1 = {"/images/cake1_1.jpg","/images/cake1_2.jpg","/images/cake1_3.jpg"};
		String[] alist2 = {"/images/cake2_1.jpg","/images/cake2_2.jpg","/images/cake2_3.jpg"};
		String[] alist3 = {"/images/cake3_1.jpg","/images/cake3_2.jpg","/images/cake3_3.jpg"};
		String[] alist4 = {"/images/cake4_1.jpg","/images/cake4_2.jpg","/images/cake4_3.jpg"};

		Cake cake1 = new Cake(1,"草莓蛋糕",228,"草莓口味",200,"/images/top1.jpg","美心西饼","14寸","偏甜",alist1);
		Cake cake2 = new Cake(2,"芒果蛋糕",198,"芒果口味",100,"/images/top2.jpg","华为西饼","15寸","甜",alist2);
		Cake cake3 = new Cake(3,"榴莲蛋糕",298,"榴莲口味",150,"/images/top3.jpg","美心西饼","16寸","微甜",alist3);
		Cake cake4 = new Cake(4,"抹茶蛋糕",298,"抹茶口味",250,"/images/top4.jpg","美心西饼","17寸","苦",alist4);

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

	@RequestMapping(value="/test")
	public String test() {
		return "controllerTest";
	}

	//微信小程序支付接口
//	@ResponseBody
//	@RequestMapping(value="/pay", method = {RequestMethod.GET} , produces = "application/json;charset=UTF-8")
//	public String pay(HttpServletRequest request){
//		Parameters paramter = new Parameters();
//		paramter.out_trade_no = request.getParameter("ordercode");
//
//		WXPayment wxPayment = new WXPayment(request.getParameter("appid"),request.getParameter("mchId"),request.getParameter("key"));
//		try {
//			WXPay wxPay =new WXPay(wxPayment);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return "";
//	}

}
