package io.github.chengmboy.cloudrs.uc.controller;

import java.security.GeneralSecurityException;

import com.alibaba.fastjson.JSONObject;
import io.github.chengmboy.cloudrs.common.web.Response;
import io.github.chengmboy.cloudrs.uc.api.dto.UserDTO;
import io.github.chengmboy.cloudrs.uc.pojo.dto.UserRegisterDTO;
import io.github.chengmboy.cloudrs.uc.pojo.vo.UserVO;
import io.github.chengmboy.cloudrs.uc.service.QuotaService;
import io.github.chengmboy.cloudrs.uc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 用户控制器
 * @author cheng_mboy
 */
@RestController
@RequestMapping("auth/user")
@RequiredArgsConstructor
@Slf4j
@Api
public class UserController {

    private final UserService userService;
    @Autowired
    private QuotaService quotaService;

    @PostMapping("register")
    public Response<UserVO> register(@Validated @ApiParam @RequestBody UserRegisterDTO user) throws GeneralSecurityException {
        log.info(JSONObject.toJSONString(user));
        UserDTO userDTO = userService.register(user);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDTO,userVO);
        return Response.ok(userVO);
    }

    @PostMapping("login")
    public Response<UserVO> login(@Validated @ApiParam @RequestBody UserRegisterDTO user) throws GeneralSecurityException {

        return Response.ok(null);
    }

    @PostMapping("quota")
    public Response<Boolean> quota(@ApiParam @RequestParam String code, @ApiParam @RequestParam int count) {
        quotaService.create(code,count);
        return Response.ok(Boolean.TRUE);
    }
}
