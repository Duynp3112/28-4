/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Time;

/**
 *
 * @author ADMIN
 */
public class Interaction {
    private String interactionId;
    private String scenario;
    private String feedback;
    private Time schedule;
    private String userId;
    private String employeeId;

    public Interaction(String interactionId, String scenario, String feedback, Time schedule, String userId, String employeeId) {
        this.interactionId = interactionId;
        this.scenario = scenario;
        this.feedback = feedback;
        this.schedule = schedule;
        this.userId = userId;
        this.employeeId = employeeId;
    }

    
    public String getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(String interactionId) {
        this.interactionId = interactionId;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Time getSchedule() {
        return schedule;
    }

    public void setSchedule(Time schedule) {
        this.schedule = schedule;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
}
