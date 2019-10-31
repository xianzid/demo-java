package com.example.demo.test.pattern.structure.proxy;

import com.example.demo.dao.IDeptDao;
import com.example.demo.dao.impl.DeptDaoImpl;
import com.example.demo.model.Dept;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 事务处理进阶-泛化Dao对象
 * 实现InvocationHandler接口就表示是一个动态代理类
 */
public class SimpleTransactionProxy implements InvocationHandler {
    //动态代理类必须要有真实对象
    private Object obj;

    /**
     * 将要操作的真实主题对象 绑定到 代理之中
     *
     * @param obj 真实主题对象
     * @return 返回代理主题类对象
     */
    Object bind(Object obj) {
        this.obj = obj;//保存真实主题对象
        //此时的代理对象是由java系统自动生成的一个代理对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    /**
     *只要执行操作方法，就一定会触发invoke()
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object retObj = null;//接收返回值
        if (method.getName().contains("create")){//更新操作
            this.prepare();
            try {
                retObj = method.invoke(this.obj, args);//反射调用方法
                this.commit();
            }catch (Exception e){
                this.rollback();
            }
        } else {
            retObj = method.invoke(this.obj, args);//查询操作不需要事务支持
        }
        return retObj;
    }

    private void prepare() {
        System.out.println("取消掉JDBC的自动提交");
    }

    private void commit() {
        System.out.println("手工提交JDBC事务");
    }

    private void rollback() {
        System.out.println("出现异常，手工回滚JDBC事务");
    }
}

/**
 * DAO工厂
 */
class DaoFactory{
    /**
     * 生产一个绑定了真实主题为DeptDao的动态代理对象
     * @return
     */
    public static Object getIDeptDaoInstance(){
        return new SimpleTransactionProxy().bind(new DeptDaoImpl());
    }
}

/**
 * 测试类
 */
class SimpleTransactionProxyTest{
    public static void main(String[] args) throws Exception {
        IDeptDao deptDaoInstance = (IDeptDao)DaoFactory.getIDeptDaoInstance();
        deptDaoInstance.create(new Dept());
        System.out.println("");
        deptDaoInstance.findAll();
    }
}
