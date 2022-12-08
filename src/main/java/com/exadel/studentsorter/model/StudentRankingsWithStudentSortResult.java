package com.exadel.studentsorter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StudentRankingsWithStudentSortResult {
    SortResult studentSortResult;
    List<StudentRanking> studentRankings;
    String resultFileName;
}
