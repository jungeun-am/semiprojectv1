package com.example.jungeun.semiprojectv1.repository;

import com.example.jungeun.semiprojectv1.domain.MemberDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {

    @Insert("insert into members(userid, password, name, email) values(#{userid},#{password},#{name},#{email}) ")
    int insertMember(MemberDTO member);

}