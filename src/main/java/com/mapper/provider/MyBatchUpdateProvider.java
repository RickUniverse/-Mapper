package com.mapper.provider;

import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.expression.spel.ast.NullLiteral;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

/**
 * @author lijichen
 * @date 2020/12/12 - 20:21
 */
public class MyBatchUpdateProvider extends MapperTemplate {
    public MyBatchUpdateProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 该方法的名字必须与:MyBatchUpdateMapper接口中的:batchUpdate()方法名一致
     *           @UpdateProvider(type = MyBatchUpdateProvider.class,method = "dynamicSQL")
     *           void batchUpdate(List<T> list);
     * @return 必须返回当前的字符串,否则会报错,找不到该方法的错
     * @param statement
     */
    public String batchUpdate(MappedStatement statement){
        StringBuilder builder = new StringBuilder();
        builder.append("<foreach collection=\"list\" item=\"record\" separator=\";\">");

        Class<?> entityClass = super.getEntityClass(statement);

        String tableName = super.tableName(entityClass);

        //生成update
        String updateClause = SqlHelper.updateTable(entityClass, tableName);

        builder.append(updateClause);

        builder.append("<set>");

        Set<EntityColumn> columns = EntityHelper.getColumns(entityClass);

        String idColumn = null;
        String idHolder = null;

        //处理修改字段
        for (EntityColumn column : columns) {

            boolean isPrimaryKey = column.isId();
            if (isPrimaryKey) {
                idColumn = column.getColumn();
                idHolder = column.getColumnHolder("record");
                continue;
            }

            String column1 = column.getColumn();
            //获取返回格式是:#{entityName.age,jdbcType=NUMERIC,typeHandler=MyTypeHandler}的字符串
            String columnHolder = column.getColumnHolder("record");
            builder.append(column1).append("=").append(columnHolder).append(",");
        }

        builder.append("</set>");

        //拼接上where语句
        builder.append("where ").append(idColumn).append("=").append(idHolder);

        builder.append("</foreach>");

        return builder.toString();
    }
}
