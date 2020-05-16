package com.demo.controller;

import com.demo.service.BankAservice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BankAController
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/5/17 2:29
 * @Version 1.0
 */
@Api(value = "Pay",tags={"银行"})
@RestController
@RequestMapping("/pay/banka")
public class BankAController {

    @Autowired
    private BankAservice bankAservice;

    @ApiOperation(value="jav->jav_v1")
    @RequestMapping(value = "/v1/transfer", method = RequestMethod.POST)
    public String transfer(@RequestParam Long money){
        return bankAservice.transfer(money);
    }

}
