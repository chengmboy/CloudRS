package io.github.chengmboy.cloudrs.payment.controller;

import java.math.BigDecimal;
import io.github.chengmboy.cloudrs.common.exception.EntityAlreadyExistException;
import io.github.chengmboy.cloudrs.common.util.UserUtils;
import io.github.chengmboy.cloudrs.common.web.Response;
import io.github.chengmboy.cloudrs.payment.api.BalanceRemoteService;
import io.github.chengmboy.cloudrs.payment.api.dto.BalanceDTO;
import io.github.chengmboy.cloudrs.payment.api.exception.OutOfMoneyException;
import io.github.chengmboy.cloudrs.payment.service.BalanceService;
import io.github.chengmboy.cloudrs.uc.api.UcRemoteService;
import io.github.chengmboy.cloudrs.uc.api.dto.UserDTO;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author cheng_mboy
 */
@RestController
@RequiredArgsConstructor
public class BalanceController implements BalanceRemoteService {

    final BalanceService balanceService;
    final UcRemoteService ucRemoteService;

    @Override
    public Response<String> query() {
//        String loginName = jwt.getSubject();
        /*UserDTO user = userRemoteService.getByLoginName(loginName);
        BalanceDTO balance = balanceService.query(user.getId());
        return Response.ok(balance);*/
        String name=UserUtils.getUser();
        UserDTO user = ucRemoteService.getByLoginName(name);
        if (user == null) {
            return Response.ok("Hello anonymous user");
        }
        return Response.ok(String.format("Hello, %s!, id: %s", user.getName(),user.getId()));
    }

    @Override
    public Response<BalanceDTO> recharge(
            @ApiParam(required = true, value = "账户id")
            @RequestParam Long balanceId, @ApiParam(required = true, value = "充值金额") @RequestParam BigDecimal num){
        BalanceDTO balance = balanceService.recharge(balanceId, num);
        return Response.ok(balance);
    }

    @Override
    public Response<BalanceDTO> create(@RequestParam Long userId) throws EntityAlreadyExistException {
        BalanceDTO balance = balanceService.create(userId);
        return Response.ok(balance);
    }

    @Override
    public Response<BalanceDTO> freeze(@RequestParam Long balanceId, @RequestParam BigDecimal num) throws OutOfMoneyException {
        BalanceDTO balance = balanceService.freeze(balanceId, num);
        return Response.ok(balance);
    }

    @Override
    public Response freeze(@RequestParam Long freezeId){
        balanceService.deduct(freezeId);
        return Response.ok();
    }

    @Override
    public Response cancel(@RequestParam Long freezeId){
        balanceService.cancel(freezeId);
        return Response.ok();
    }
}
