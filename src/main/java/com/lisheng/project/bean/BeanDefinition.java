package com.lisheng.project.bean;

/**
 * @ClassName： BeanDefinitionImpl
 * @description:
 * @author: 李胜
 * @create: 2020-06-06 14:47
 **/
public abstract class BeanDefinition {
    /**
     *功能描述
     * @author 李胜
     * @date 2020/6/6
     * @param value 对象中这个属性原本的值
     * @return Object 这个属性的值经过转化逻辑以后的返回值，请注意类型匹配
     **/
    public abstract Object transerMethod(Object value);
}
