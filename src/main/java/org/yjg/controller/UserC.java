package org.yjg.controller;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yjg.entity.Result;
import org.yjg.entity.protocal.LogInJson;
import org.yjg.service.IUserService;
import org.yjg.util.Constant;

@Controller
@RequestMapping("/user")
public class UserC {

    /**
     * 此方法, 使用来返回login的值, 会自动交给viewResolver中去解析进行页面匹配
     * 在spring-config.xml配置中根据prefix于suffix拼接成所需要的页面
     *
     * @return
     */
    @RequestMapping("/testLogin")
    public String testlogin() {
        return "login";
    }

    /**
     * 与上述方法比较, 是否添加@ResponseBody 的区别在于, 如果没有该注释, 返回值是默认去
     * 视图层寻找匹配, 那么只需要加上@ResponseBody注释, 则能以正常的Http请求进行值的返回
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test() {
        return "test";
    }

    /**
     * 以下为正常业务流程
     */
    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody LogInJson login) {
        String username = login.getUsername();
        String password = login.getPassword();

        try {
            Preconditions.checkNotNull(username, "username cannot be null");
            Preconditions.checkNotNull(password, "password cannot be null");
            String res = userService.login(username, password);
            if (Constant.OK.equals(res)) {
                return Result.success();
            } else {
                return Result.error(res, res);
            }
        } catch (NullPointerException e) {
            return Result.error(Constant.PARAM_ERROR, e.getMessage());
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody LogInJson logInJson) {
        String username = logInJson.getUsername();
        String password = logInJson.getPassword();
        String password_twice = logInJson.getPassword_twice();

        try {
            //guava 工具类, 检查参数是否为空, 如果为空抛出 message为定义的第二个参数的值的 空指针异常
            Preconditions.checkNotNull(username, "username cannot be null");
            Preconditions.checkNotNull(password, "password cannot be null");
            Preconditions.checkNotNull(password_twice, "password_twice cannot be null");

            String res = userService.register(username, password, password_twice);
            if (Constant.OK.equals(res)) {
                return Result.success();
            }
            return Result.error(res, res);
        } catch (NullPointerException e) {
            return Result.error(Constant.PARAM_ERROR, e.getMessage());
        } catch (Exception e) {
            return Result.error(e);
        }
    }
}

