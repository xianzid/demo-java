package com.example.demo.test.pattern.structure.proxy;

import com.example.demo.dao.IDeptDao;
import com.example.demo.dao.impl.DeptDaoImpl;
import com.example.demo.model.Dept;

import java.util.List;

/**
 * 模仿JDBC做简单事务处理-类似AOP
 * 使用代理工厂类
 */
public class SimpleTransactionDeptProxy implements IDeptDao {
    private IDeptDao deptDao;

    SimpleTransactionDeptProxy(IDeptDao deptDao) {
        this.deptDao = deptDao;
    }

    @Override
    public boolean create(Dept dept) throws Exception{
        try {
            prepare();
            boolean flag = deptDao.create(dept);
            commit();
            return flag;
        }catch (Exception e){
            rollback();
            throw e;
        }
    }

    @Override
    public List<Dept> findAll() {
        return deptDao.findAll();
    }

    private void prepare(){
        System.out.println("取消掉JDBC的自动提交");
    }

    private void commit(){
        System.out.println("手工提交JDBC事务");
    }

    private void rollback(){
        System.out.println("出现异常，手工回滚JDBC事务");
    }

    /*public void setDeptDao(IDeptDao deptDao) {
        this.deptDao = deptDao;
    }*/
}

class DeptDaoFactory{
    static IDeptDao getDeptDaoInstance(){
        //只用实现类就可以完成的，不用经过代理
        //return new DeptDaoImpl().getInstance();
        //需要代理在前后加上比如事务的处理的
        return new SimpleTransactionDeptProxy(new DeptDaoImpl());
    }
}


class SimpleTransactionDeptProxyTest{
    public static void main(String[] args) throws Exception {
        //初始化代理对象
        IDeptDao deptDao = DeptDaoFactory.getDeptDaoInstance();
        //调用代理创建方法
        deptDao.create(new Dept());
        Thread.sleep(50000);
        System.out.println("");
        //调用代理查询方法
        deptDao.findAll();
    }
}


