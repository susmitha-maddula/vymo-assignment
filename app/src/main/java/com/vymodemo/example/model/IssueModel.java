package com.vymodemo.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueModel {

  @JsonProperty("number")
  public Long number;

  @JsonProperty("title")
  public String title;

  @JsonProperty("state")
  public String state;

  @JsonProperty("updated_at")
  public String updated_at;

  public IssueModel() {

  }

  @Override
  public String toString() {
    return "IssueModel{" +
        "number=" + number +
        ", title='" + title + '\'' +
        ", state='" + state + '\'' +
        ", updated_at='" + updated_at + '\'' +
        '}';
  }
}
