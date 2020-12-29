package com.mapper.mymapper;

import com.mapper.provider.MyBatchUpdateProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @author lijichen
 * @date 2020/12/12 - 20:22
 */
public interface MyBatchUpdateMapper<T> {

    @UpdateProvider(type = MyBatchUpdateProvider.class,method = "dynamicSQL")
    void batchUpdate(List<T> list);
}
