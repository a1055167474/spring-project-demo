package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.entity.UserDto.UserDto;
import com.example.SpringProjectDemo.service.RedisService;
import com.example.SpringProjectDemo.service.UserService;
import com.example.SpringProjectDemo.utils.ResultUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author qinzhibin
 * @description TODO
 * @date 2021年03月23日
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;


    /**
     * @Description: 获取所有用户信息
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/25
     */
    @RequestMapping(value = "/getAllUser",method = RequestMethod.GET)
    @ApiOperation(value = "/getAllUser",notes = "获取所有用户信息", httpMethod = "GET", produces = "application/json", consumes = "application/json")
    @ApiResponses({@ApiResponse(code = 200, message="操作成功", response = Response.class), @ApiResponse(code = 500, message = "操作失败", response = Response.class)})
    public Response<List<User>> getAllUser(){

        try {
            List<User> users = userService.getALlUser();
            return ResultUtils.ResultSuccessUtilMessage(users, "查询成功");
        }catch (Exception e){
            return ResultUtils.ResultErrorUtil("查询异常");
        }

    }


    /**
     * @Description: 分页获取所有用户信息
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/25
     */
    @RequestMapping(value = "/getUserByPage",method = RequestMethod.GET)
    @ApiOperation(value = "/getUserByPage",notes = "分页获取所有用户信息", httpMethod = "GET", produces = "application/json", consumes = "application/json")
    @ApiResponses({@ApiResponse(code = 200, message="操作成功", response = Response.class), @ApiResponse(code = 500, message = "操作失败", response = Response.class)})
    public Response<List<User>> getUserByPage(@RequestParam Integer page, @RequestParam Integer size){
        try{
            List<User> users = userService.getAllUser(page,size);
            return ResultUtils.ResultSuccessUtilMessage(users,"查询成功");
        }catch (Exception e){
            return ResultUtils.ResultErrorUtil("查询异常");
        }
    }


    /**
     * @Description: 根据用户名获取用户信息
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/26
     */
    @RequestMapping(value = "/getUserByName",method = RequestMethod.GET)
    @ApiOperation(value = "/getUserByName",notes = "根据用户名获取用户信息", httpMethod = "GET", produces = "application/json", consumes = "application/json")
    @ApiResponses({@ApiResponse(code = 200, message="操作成功", response = Response.class), @ApiResponse(code = 500, message = "操作失败", response = Response.class)})
    public Response<User> getUserByPage(@RequestParam String name){
        try{
            User user = userService.getUserByName(name);
            return ResultUtils.ResultSuccessUtilMessage(user,"查询成功");
        }catch (Exception e){
            return ResultUtils.ResultErrorUtil("查询异常");
        }
    }

    /**
     * @Description: 新增一个用户
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/26
     */
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "/addUser",notes = "新增一个用户", httpMethod = "POST", produces = "application/json", consumes = "application/json")
    @ApiResponses({@ApiResponse(code = 200, message="操作成功", response = Response.class), @ApiResponse(code = 500, message = "操作失败", response = Response.class)})
    public Response<?> addUser(@RequestBody User user){

        if(org.apache.commons.lang3.StringUtils.isBlank(user.getUserAccount()) ||
                org.apache.commons.lang3.StringUtils.isBlank(user.getPassword())){
            return ResultUtils.ResultErrorUtil("用户名或密码为空");
        }

        try{
            Response<?> response  = userService.addUser(user);
            return response;
        }catch (Exception e){
            return ResultUtils.ResultErrorUtil("新增用户异常");
        }
    }


    /**
     * @Description: 新增一个用户(用于测试实体类参数校验注解)
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/26
     */
    @RequestMapping(value = "/addUserTemp",method = RequestMethod.POST)
    @ApiOperation(value = "/addUserTemp",notes = "新增一个用户", httpMethod = "POST", produces = "application/json", consumes = "application/json")
    @ApiResponses({@ApiResponse(code = 200, message="操作成功", response = Response.class), @ApiResponse(code = 500, message = "操作失败", response = Response.class)})
    public Response<?> addUserTemp(@RequestBody  @Valid UserDto user){
        try{
            Response<?> response  = new Response<>();
            return response;
        }catch (Exception e){
            return ResultUtils.ResultErrorUtil("新增用户异常");
        }
    }


}
