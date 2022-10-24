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

import java.math.BigDecimal;
import java.time.Year;
import java.util.Date;

/**
 * COSSInvestBean
 *
 * @author Icemap
 * @date 2022/10/20
 */
@Entity
@Table(name = "coss_invest")
public class COSSInvestBean {
    /**
     * +---------------+---------------------------------------------------+------+-----+---------+----------------+
     * | Field         | Type                                              | Null | Key | Default | Extra          |
     * +---------------+---------------------------------------------------+------+-----+---------+----------------+
     * | id            | int(11)                                           | NO   | PRI | <null>  | auto_increment |
     * | company       | varchar(255)                                      | YES  |     | <null>  |                |
     * | repository    | varchar(255)                                      | YES  |     | <null>  |                |
     * | stage         | enum('Seed','A','B','C','D','E','F','G','Growth') | YES  |     | <null>  |                |
     * | round_size    | decimal(10,2)                                     | YES  |     | <null>  |                |
     * | year          | year(4)                                           | YES  |     | <null>  |                |
     * | month         | int(11)                                           | YES  |     | <null>  |                |
     * | lead_investor | varchar(255)                                      | YES  |     | <null>  |                |
     * | has_repo      | tinyint(1)                                        | YES  |     | <null>  |                |
     * | has_github    | tinyint(1)                                        | YES  |     | <null>  |                |
     * | github_name   | varchar(255)                                      | YES  |     | <null>  |                |
     * | date          | datetime                                          | YES  |     | <null>  |                |
     * +---------------+---------------------------------------------------+------+-----+---------+----------------+
     */
    public enum Stage { Seed, A, B, C, D, E, F, G, Growth }

    @Id
    private Integer id;

    @Column(name = "company")
    private String company;

    @Column(name = "repository")
    private String repository;

    @Column(name = "stage")
    @Enumerated(EnumType.STRING)
    private Stage stage;

    @Column(name = "round_size")
    private BigDecimal roundSize;

    @Column(name = "year")
    private Year year;

    @Column(name = "month")
    private Integer month;

    @Column(name = "lead_investor")
    private String leadInvestor;

    @Column(name = "has_repo")
    private Boolean hasRepo;

    @Column(name = "has_github")
    private Boolean hasGithub;

    @Column(name = "github_name")
    private String githubName;

    @Column(name = "date")
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public BigDecimal getRoundSize() {
        return roundSize;
    }

    public void setRoundSize(BigDecimal roundSize) {
        this.roundSize = roundSize;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getLeadInvestor() {
        return leadInvestor;
    }

    public void setLeadInvestor(String leadInvestor) {
        this.leadInvestor = leadInvestor;
    }

    public Boolean getHasRepo() {
        return hasRepo;
    }

    public void setHasRepo(Boolean hasRepo) {
        this.hasRepo = hasRepo;
    }

    public Boolean getHasGithub() {
        return hasGithub;
    }

    public void setHasGithub(Boolean hasGithub) {
        this.hasGithub = hasGithub;
    }

    public String getGithubName() {
        return githubName;
    }

    public void setGithubName(String githubName) {
        this.githubName = githubName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
