package com.akshitha.Resume_Analyzer.controller;

import com.akshitha.Resume_Analyzer.model.ResumeResponse;
import org.apache.tika.Tika;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @PostMapping("/upload")
    public ResumeResponse analyzeResume(@RequestParam("file") MultipartFile file) {

        try {
            Tika tika = new Tika();
            String text = tika.parseToString(file.getInputStream()).toLowerCase();

            int score = 0;
            List<String> skills = new ArrayList<>();
            List<String> recommendations = new ArrayList<>();

            // ---------------- SKILL SCORING ----------------
            if (text.contains("java")) {
                score += 20;
                skills.add("Java");
            } else recommendations.add("Add Java skills");

            if (text.contains("spring")) {
                score += 25;
                skills.add("Spring Boot");
            } else recommendations.add("Add Spring Boot experience");

            if (text.contains("rest")) {
                score += 15;
                skills.add("REST APIs");
            } else recommendations.add("Mention REST API projects");

            if (text.contains("project")) {
                score += 15;
            } else recommendations.add("Add project experience");

            if (text.contains("sql") || text.contains("mysql")) {
                score += 15;
                skills.add("Databases");
            }

            // ---------------- EXPERIENCE LEVEL ----------------
            String experience;
            if (text.contains("intern") || text.contains("fresher")) {
                experience = "Fresher";
            } else if (text.contains("years")) {
                experience = "Experienced";
            } else {
                experience = "Entry Level";
            }

            // ---------------- JOB ROLE DETECTION ----------------
            String jobRole;
            if (text.contains("spring") || text.contains("java")) {
                jobRole = "Java Backend Developer";
            } else if (text.contains("python")) {
                jobRole = "Python Developer";
            } else {
                jobRole = "General Developer";
            }

            // ---------------- JOB MATCH SCORE ----------------
            double jobMatch = Math.min(100, score + 10);

            // ---------------- STATUS ----------------
            String status = (score >= 70) ? "Strong Profile"
                    : (score >= 40) ? "Average Profile"
                      : "Weak Profile";

            return new ResumeResponse(
                    score,
                    experience,
                    jobRole,
                    jobMatch,
                    skills,
                    recommendations,
                    status
            );

        } catch (Exception e) {
            return new ResumeResponse(0, "Error", "Error", 0,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    "File processing failed");
        }
    }
}
