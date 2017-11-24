package com.chentaiyi.common.dao;

import com.chentaiyi.domain.User;
import org.junit.AfterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;

/**
 * Created by hasee on 2017/11/20.
 */
@ContextConfiguration("classpath*:/mypath-context.xml")
public class BaseDaoTest extends UnitilsTestNG {



    @Test
    public void testSetJdbcTemplate() throws Exception {

    }

    @Test
    public void testSetSqlPageHandler() throws Exception {

    }

    @Test
    public void testGetRowForObject() throws Exception {

    }

    @Test
    public void testGetRows() throws Exception {

    }

    @Test
    public void testAddUpdateDelete() throws Exception {

    }

    @Test
    public void testInsertAndGetId() throws Exception {

    }

    @Test
    public void testBatchUpdate() throws Exception {

    }

    @Test
    public void testGetMapData() throws Exception {

    }

    @Test
    public void testQueryList() throws Exception {

    }

    @Test
    public void testFindPage() throws Exception {

    }
}
