package moe.dazecake.arklightscloudbackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import moe.dazecake.arklightscloudbackend.entity.AccountEntity;
import moe.dazecake.arklightscloudbackend.mapper.AccountMapper;
import moe.dazecake.arklightscloudbackend.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

@Tag(name = "账号接口")
@ResponseBody
@RestController
public class AccountController {

    @Resource
    AccountMapper accountMapper;

    @Operation(summary = "增加账号")
    @PostMapping("/addAccount")
    public Result<String> addAccount(@RequestBody AccountEntity accountEntity){
        Result<String> result = new Result<>();

        accountMapper.insert(accountEntity);

        result.setCode(200);
        result.setMsg("success");
        result.setData(null);

        return result;
    }

    @Operation(summary = "删除账号")
    @PostMapping("/delAccount")
    public Result<String> delAccount(Long id){
        Result<String> result = new Result<>();

        var account = accountMapper.selectById(id);
        if (account!= null){
            account.setDelete(1);
            accountMapper.updateById(account);

            result.setCode(200);
            result.setMsg("success");

        }else {
            result.setCode(403);
            result.setMsg("Unable to delete a non-existent account");

        }
        result.setData(null);
        return result;

    }

    @Operation(summary = "分页查询账号")
    @GetMapping("/showAccount")
    public Result<ArrayList<AccountEntity>> showAccount(Long current,Long size){
        Result<ArrayList<AccountEntity>> result = new Result<>();
        result.setData(new ArrayList<>());

        var data = accountMapper.selectPage(new Page<>(current, size), null);
        result.getData().addAll(data.getRecords());

        return result;
    }

    @Operation(summary = "更新账号")
    @PostMapping("/updateAccount")
    public Result<String> updateAccount(Long id,@RequestBody AccountEntity accountEntity) {
        Result<String> result = new Result<>();

        var account = accountMapper.selectById(id);
        if (account!= null){
            account = accountEntity;
            account.setId(id);
            accountMapper.updateById(account);

            result.setCode(200);
            result.setMsg("success");

        }else {
            result.setCode(403);
            result.setMsg("Unable to update a non-existent account");

        }
        result.setData(null);
        return result;
    }

}
