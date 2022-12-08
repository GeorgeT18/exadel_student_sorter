package com.exadel.studentsorter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Properties;

@Getter
@Setter
@AllArgsConstructor
public class StudentRanking extends Properties {
    private String studentName;
    private Float studentScore;
}
