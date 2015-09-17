package com.unstruct.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by lenovo-ang on 2015/9/17.
 */
@Component
public class SuperDao {

    @Resource
    SessionFactory sessionFactory;
}
