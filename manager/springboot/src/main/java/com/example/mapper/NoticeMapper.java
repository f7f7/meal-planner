package com.example.mapper;

import com.example.entity.Notice;
import java.util.List;

/**
 * Interfaces for manipulating notice-related data
*/
public interface NoticeMapper {


    int insert(Notice notice);

    int deleteById(Integer id);

    int updateById(Notice notice);

    Notice selectById(Integer id);

    List<Notice> selectAll(Notice notice);

}