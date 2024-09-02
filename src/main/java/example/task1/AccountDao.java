package example.task1;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountDao {
    // [C] 가계부 항목 등록
    int add(AccountDto dto);

    // [R] 가계부 전체 조회
    List<AccountDto> getAll();

    // [U] 가계부 항목 수정
    int edit(AccountDto dto);

    // [D] 가계부 항목 삭제
    int delete(int id);
}
