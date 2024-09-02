package example.mybatis;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyDao {
    // 인터페이스는 추상메서드를 가진다
    // 추상메서드는 선언부만 있고 구현부가 없다

    // [1] 추상메서드

    // 1. 등록
    int save(UserDto userDto);

    // 2. 전체출력
    List<UserDto> findAll();

    // 2-1. 개별출력
    UserDto findById(int id);

    // 3. 수정
    int update(UserDto userDto);

    // 4. 삭제
    int delete(int id);
}
