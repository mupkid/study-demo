package org.ohx.studyeasyexcel.entity;

/**
 * @author mudkip
 * @date 2023/3/19
 */
public enum Sex {
    MALE("male", "男"),
    FEMALE("female", "女");

    private String sexEN;

    private String sexCN;

    Sex(String sexEN, String sexCN) {
        this.sexEN = sexEN;
        this.sexCN = sexCN;
    }
}
