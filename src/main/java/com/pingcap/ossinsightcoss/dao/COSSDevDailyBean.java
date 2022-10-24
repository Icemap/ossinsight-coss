// Copyright 2022 PingCAP, Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.pingcap.ossinsightcoss.dao;

import jakarta.persistence.*;

import java.util.Date;

/**
 * COSSDevDailyBean
 *
 * @author Icemap
 * @date 2022/10/24
 */
@Entity
@Table(name = "mv_coss_dev_day")
@IdClass(value = COSSDevDailyKey.class)
public class COSSDevDailyBean {
    @Id
    @Column(name = "github_name")
    private String githubName;
    @Id
    @Column(name = "event_day")
    private Date eventDay;

    // event fields
    @Column(name = "event_num")
    private Integer eventNum;
    @Column(name = "star_num")
    private Integer starNum;
    @Column(name = "pr_num")
    private Integer prNum;
    @Column(name = "issue_num")
    private Integer issueNum;

    // dev fields
    @Column(name = "dev_num")
    private Integer devNum;
    @Column(name = "star_dev_num")
    private Integer starDevNum;
    @Column(name = "pr_dev_num")
    private Integer prDevNum;
    @Column(name = "issue_dev_num")
    private Integer issueDevNum;

    public String getGithubName() {
        return githubName;
    }

    public void setGithubName(String githubName) {
        this.githubName = githubName;
    }

    public Date getEventDay() {
        return eventDay;
    }

    public void setEventDay(Date eventDay) {
        this.eventDay = eventDay;
    }

    public Integer getEventNum() {
        return eventNum;
    }

    public void setEventNum(Integer eventNum) {
        this.eventNum = eventNum;
    }

    public Integer getStarNum() {
        return starNum;
    }

    public void setStarNum(Integer starNum) {
        this.starNum = starNum;
    }

    public Integer getPrNum() {
        return prNum;
    }

    public void setPrNum(Integer prNum) {
        this.prNum = prNum;
    }

    public Integer getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(Integer issueNum) {
        this.issueNum = issueNum;
    }

    public Integer getDevNum() {
        return devNum;
    }

    public void setDevNum(Integer devNum) {
        this.devNum = devNum;
    }

    public Integer getStarDevNum() {
        return starDevNum;
    }

    public void setStarDevNum(Integer starDevNum) {
        this.starDevNum = starDevNum;
    }

    public Integer getPrDevNum() {
        return prDevNum;
    }

    public void setPrDevNum(Integer prDevNum) {
        this.prDevNum = prDevNum;
    }

    public Integer getIssueDevNum() {
        return issueDevNum;
    }

    public void setIssueDevNum(Integer issueDevNum) {
        this.issueDevNum = issueDevNum;
    }
}
