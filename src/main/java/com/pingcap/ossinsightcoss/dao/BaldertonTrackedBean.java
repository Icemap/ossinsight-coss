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
 * BaldertonTrackedBean
 *
 * @author Icemap
 * @date 2022/10/20
 */
@Entity
@Table(name = "balderton_tracked")
public class BaldertonTrackedBean {
    @Id
    @Column(name = "repo_name")
    private String repoName;

    @Column(name = "company_website")
    private String companyWebsite;

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }
}
