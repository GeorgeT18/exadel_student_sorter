package com.exadel.studentsorter.service;


import com.exadel.studentsorter.exception.SorterNotFoundException;
import com.exadel.studentsorter.exception.StudentFileParserException;
import com.exadel.studentsorter.model.SortResult;
import com.exadel.studentsorter.model.StudentRanking;
import com.exadel.studentsorter.model.StudentRankingsWithStudentSortResult;
import com.exadel.studentsorter.sorter.Sorter;
import com.exadel.studentsorter.util.SortUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private FileService fileService;

    public SortResult sortStudentRankings(List<StudentRanking> studentRankings, String sortingAlgorithmName) throws SorterNotFoundException {
        Sorter<StudentRanking> studentSorter = SortUtil.getSorterByName(sortingAlgorithmName, (studentRanking, studentRanking2) -> studentRanking2.getStudentScore() > studentRanking.getStudentScore() ? 1 : 0);

        return studentSorter.sort(studentRankings);
    }

    public String writeStudentRankingsToTxtFile(List<StudentRanking> studentRankings) throws FileNotFoundException {
        String fileName = fileService.generateUniqueFileName("txt");

        PrintWriter out = new PrintWriter(fileService.getPath(fileName));

        for (StudentRanking studentRanking : studentRankings) {
            out.println(studentRanking.getStudentName() + "," + studentRanking.getStudentScore());
        }

        out.close();

        return fileName;
    }

    public List<StudentRanking> processFileToStudentRankings(MultipartFile multipartFile) throws IOException, StudentFileParserException {
        String fileName = fileService.uploadFile(multipartFile);

        try {
            return Files.readAllLines(Paths.get(fileService.getPath(fileName)), StandardCharsets.UTF_8).stream().map(line -> {
                List<String> values = Arrays.asList(line.split("\\s*,\\s*"));

                String name = values.get(0);
                Float score = Float.parseFloat(values.get(1));

                return new StudentRanking(name, score);
            }).collect(Collectors.toList());
        } catch (Exception exception) {
            throw new StudentFileParserException("the given file could not be parsed into a list of students");
        }
    }

    public StudentRankingsWithStudentSortResult processSortSaveFileAndGetStudentRankings(MultipartFile multipartFile, String sortingAlgorithmName) throws IOException, SorterNotFoundException, StudentFileParserException {
        // turn the file into an arrayList
        List<StudentRanking> studentRankings = this.processFileToStudentRankings(multipartFile);

        // sort the returned arraylist
        SortResult studentSortResult = this.sortStudentRankings(studentRankings, sortingAlgorithmName);

        // create a .txt file of sorted students
        String fileName = this.writeStudentRankingsToTxtFile(studentRankings);

        return new StudentRankingsWithStudentSortResult(studentSortResult, studentRankings, fileName);
    }
}
