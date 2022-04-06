package com.zhny.starter.log.common;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class City implements Serializable {

    String country;

    String province;

    String city;
}
