package example.task1;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    // [C] 가계부 항목 등록
    public int add(AccountDto dto){
        int result = accountDao.add(dto);
        if(result == 1) {
            log.info("successfully added.");
            return result;
        }
        else{
            return 0;
        }
    }

    // [R] 가계부 전체 조회
    public List<AccountDto> getAll(){
        return accountDao.getAll();
    }

    // [U] 가계부 항목 수정
    public int edit(AccountDto dto){
        int result = accountDao.edit(dto);
        if(result == 1) {
            log.info("successfully edited.");
            return result;
        }
        else{
            return 0;
        }
    }

    // [D] 가계부 항목 삭제
    public int delete(int id){
        int result = accountDao.delete(id);
        if(result == 1) {
            log.info("successfully deleted.");
            return result;
        }
        else{
            return 0;
        }
    }
}
