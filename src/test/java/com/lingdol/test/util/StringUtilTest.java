package com.lingdol.test.util;

import com.lingdol.common.util.StringUtil;
import org.junit.Test;

public class StringUtilTest {

    @Test
    public void padTest() {
        System.out.println(StringUtil.leftPad("1", 5, '0'));
        System.out.println(StringUtil.rightPad("2", 5, '0'));
    }

}
