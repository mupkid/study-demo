package org.ohx.studyhttpclient;

/**
 * @author mudkip
 * @date 2022/12/27
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(HttpClientUtils.get("https://c.m.163.com/ug/api/wuhan/app/data/list-total"));
    }
}
