package com.chr.shoppingmall.controller;


import com.chr.shoppingmall.service.Impl.loginServiceImpl;
import com.chr.shoppingmall.service.Impl.pageServiceImpl;
import com.chr.shoppingmall.service.Impl.sellerServiceImpl;
import com.chr.shoppingmall.utils.loginUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: 程浩然
 * @Create: 2024/9/22 - 10:40
 * @Description: 用户登录和注册
 */
@Controller
public class login {
    @Autowired
    private loginServiceImpl loginService;
    @Autowired
    private sellerServiceImpl sellerService;
    @Autowired
    private pageServiceImpl pageService;

    /**
     * 登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginShow() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session,
                        @RequestParam("name") String name,
                        @RequestParam("password") String password,
                        @RequestParam("phone") String phone,
                        @RequestParam("Verification_code") String inputVerificationCode
    ) {
        String codeTrue = loginUtil.getVerificationCode(phone);
        Integer i = loginService.login(name, password, password, phone, inputVerificationCode, codeTrue);
        Integer j = sellerService.sellerLogin(name, password, password, phone, inputVerificationCode, codeTrue);
        if (j > 0) {
            session.setAttribute("sellerId", j);
            session.setAttribute("sellerName", name);
            return "redirect:/seller/sellerProfile";
        }
        switch (i) {
            case -1, -4:
                session.setAttribute("loginEx", 2);
                return "login";
            case -2, -5:
                session.setAttribute("loginEx", 0);
                return "login";
            case -3:
                session.setAttribute("loginEx", 1);
                return "login";
            default:
                session.setAttribute("userId", i);
                return "redirect:/";
        }
    }


    /**
     * 注册页面
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerShow() {
        return "register";
    }

    /**
     * 注册页面处理
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
            HttpSession session,
            @RequestParam("name") String name,
            @RequestParam("password") String password1,
            @RequestParam("password2") String password2,
            @RequestParam("phone") String phone,
            @RequestParam("emil") String emil,
            @RequestParam("Verification_code") String inputVerificationCode
    ) {
        String code = loginUtil.getVerificationCode(phone);
        Integer i = loginService.register(name, password1, password2, inputVerificationCode, code, emil, phone);
        switch (i) {
            case 0:
                return "login";
            case -1, -2, -3, -4:
                session.setAttribute("registerEx", -i);
                return "register";
        }
        return "register";
    }
}
