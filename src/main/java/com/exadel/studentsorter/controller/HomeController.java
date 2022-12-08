package com.exadel.studentsorter.controller;

import com.exadel.studentsorter.exception.SorterNotFoundException;
import com.exadel.studentsorter.exception.StudentFileParserException;
import com.exadel.studentsorter.model.StudentFilePostDto;
import com.exadel.studentsorter.model.StudentRankingsWithStudentSortResult;
import com.exadel.studentsorter.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String home() {
        return "home";
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String submit(@ModelAttribute StudentFilePostDto studentFilePostDto, Model model) throws SorterNotFoundException, IOException, StudentFileParserException {
        StudentRankingsWithStudentSortResult studentRankingsWithStudentSortResult = studentService.processSortSaveFileAndGetStudentRankings(studentFilePostDto.getFile(), studentFilePostDto.getSortingAlgorithm());

        model.addAttribute("studentRankings", studentRankingsWithStudentSortResult.getStudentRankings());
        model.addAttribute("sortTimeTakenInMilliSeconds", studentRankingsWithStudentSortResult.getStudentSortResult().getTimeTakenInMilliSeconds());
        model.addAttribute("sortedFileName", studentRankingsWithStudentSortResult.getResultFileName());

        return "result";
    }
}
