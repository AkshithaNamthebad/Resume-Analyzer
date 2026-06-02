package com.akshitha.Resume_Analyzer.model;

import java.util.List;

public class ResumeResponse {

    private int atsScore;
    private String experienceLevel;
    private String jobRole;
    private double jobMatchPercent;
    private List<String> skillsFound;
    private List<String> recommendations;
    private String status;

    public ResumeResponse(int atsScore, String experienceLevel, String jobRole,
                          double jobMatchPercent, List<String> skillsFound,
                          List<String> recommendations, String status) {
        this.atsScore = atsScore;
        this.experienceLevel = experienceLevel;
        this.jobRole = jobRole;
        this.jobMatchPercent = jobMatchPercent;
        this.skillsFound = skillsFound;
        this.recommendations = recommendations;
        this.status = status;
    }

    // getters + setters
    public int getAtsScore() { return atsScore; }
    public String getExperienceLevel() { return experienceLevel; }
    public String getJobRole() { return jobRole; }
    public double getJobMatchPercent() { return jobMatchPercent; }
    public List<String> getSkillsFound() { return skillsFound; }
    public List<String> getRecommendations() { return recommendations; }
    public String getStatus() { return status; }
}