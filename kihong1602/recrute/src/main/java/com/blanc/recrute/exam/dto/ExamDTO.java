package com.blanc.recrute.exam.dto;

import java.time.LocalDate;

public class ExamDTO {

  private String title;
  private LocalDate startDate;
  private LocalDate endDate;

  public ExamDTO(String title, LocalDate startDate, LocalDate endDate) {
    this.title = title;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public ExamDTO(Builder builder) {
    this.title = builder.title;
    this.startDate = builder.startDate;
    this.endDate = builder.endDate;
  }


  public String getTitle() {
    return title;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public static class Builder {

    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder startDate(LocalDate startDate) {
      this.startDate = startDate;
      return this;
    }

    public Builder endDate(LocalDate endDate) {
      this.endDate = endDate;
      return this;
    }

    public ExamDTO build() {
      return new ExamDTO(this);
    }
  }
}
