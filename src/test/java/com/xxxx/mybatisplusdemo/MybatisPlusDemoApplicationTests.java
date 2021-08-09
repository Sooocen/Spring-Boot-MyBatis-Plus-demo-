package com.xxxx.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.mybatisplusdemo.mapper.UserMapper;
import com.xxxx.mybatisplusdemo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusDemoApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    //查询全部数据
    @Test
    public void FindAll(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    //添加
    @Test
    public void TestAdd(){
        User user = new User();
        user.setId(68L);
        user.setName("Jerry");
        user.setAge(28);
        user.setEmail("Sooocen@163.com");
        int result = userMapper.insert(user);
        System.out.println(result);
    }

    //修改
    @Test
    public void TestUpdate(){
        User user = new User();
        user.setId(66L);
        user.setName("Marry1");
        int count = userMapper.updateById(user);
        System.out.println(count);
    }

    //乐观锁
    @Test
    public void TestOptimisticLocker(){
        //根据Id查询
        User user = userMapper.selectById(66L);
        Integer version = user.getVersion();
        user.setName("zhangsan");
        userMapper.updateById(user);

    }

    //多id查询(批量查询)
    @Test
    public void TestSelect1(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1,2,3));
        System.out.println(users);
    }

    //简单条件查询
    @Test
    public void TestSelect2(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","Jack");
        map.put("age","20");
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }

    //分页查询
    @Test
    public void TestSelectPage(){
        Page<User> page = new Page(1,3);
        Page userPage = userMapper.selectPage(page,null);
        long pages = userPage.getPages(); //总页数
        long current = userPage.getCurrent(); //当前页
        List records = userPage.getRecords(); //查询数据的集合
        long total = userPage.getTotal(); //总记录数
        boolean hasNext = userPage.hasNext(); //是否有下一页
        boolean hasPrevious = userPage.hasPrevious(); //是否有上一页
    }


    //通过ID删除
    @Test
    public void TestDeleteById(){
        userMapper.deleteById(68L);
    }

    //批量删除
    @Test
    public void TestDelete(){
        userMapper.deleteBatchIds(Arrays.asList(66,67));
    }

    //逻辑删除 添加列表项和注解后 普通删除即可
    @Test
    public void TestLogicDelete(){
        userMapper.deleteById(68L);
    }

    //复杂查询操作
    @Test
    public void TestSelect(){
        QueryWrapper queryWrapper = new QueryWrapper<User>();
        //ge(大等)   gt(大于)   le(小等)   lt(小于)  参数一:字段名称 参数二:属性值
        //queryWrapper.ge("age",21);

        //eq(等于)   ne(不等于)
        //queryWrapper.eq("name","tom");

        //between(在某区间)   notbetween(不在某区间)  参数一:字段名称 参数二:区间左边界 参数三:区间右边界
        //queryWrapper.between("age",24,28);

        //like(模糊查询%张%)   notlike(模糊查询%张%)   likeleft(模糊查询%张)   likeright(模糊查询张%)  参数一:字段名称 参数二:属性值
        //queryWrapper.like("name","张");

        //orderby orderbydesc(降序排列)   orderbyasc(升序排列)
        queryWrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);


    }


}
