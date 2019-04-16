package com.car.shopping.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: 72038667
 * @Date: 2019/4/4 15:17
 * @Version: 1.0
 */

public class MenusVo {

    private String name;

    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MenusVo() {
    }

    public MenusVo(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
