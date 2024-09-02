package example.task1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    // [C] 가계부 항목 등록
    @PostMapping("/add")
    public int add(AccountDto dto){
        return accountService.add(dto);
    }

    // [R] 가계부 전체 조회
    @GetMapping("/all")
    public List<AccountDto> getAll(){
        return accountService.getAll();
    }

    // [U] 가계부 항목 수정
    @PutMapping("/edit")
    public int edit(AccountDto dto){
        return accountService.edit(dto);
    }

    // [D] 가계부 항목 삭제
    @DeleteMapping("/delete")
    public int delete(int id){
        return accountService.delete(id);
    }

}
