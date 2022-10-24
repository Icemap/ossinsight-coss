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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * COSSDevDailyRepository
 *
 * @author Icemap
 * @date 2022/10/24
 */
@Repository
public interface COSSDevDailyRepository extends JpaRepository<COSSDevDailyBean, Long> {
    @Query(value = """
    SELECT
        ci.github_name,
        ge.event_day,
    
        COUNT(*) event_num,
        COUNT(CASE WHEN ge.type = "WatchEvent" THEN 1 ELSE NULL END) AS star_num,
        COUNT(CASE WHEN ge.type = "PullRequestEvent" THEN 1 ELSE NULL END) AS pr_num,
        COUNT(CASE WHEN ge.type = "IssuesEvent" THEN 1 ELSE NULL END) AS issue_num,
    
        COUNT(DISTINCT ge.actor_id) dev_num,
        COUNT(DISTINCT CASE WHEN ge.type = "WatchEvent" THEN ge.actor_id ELSE NULL END) AS star_dev_num,
        COUNT(DISTINCT CASE WHEN ge.type = "PullRequestEvent" THEN ge.actor_id ELSE NULL END) AS pr_dev_num,
        COUNT(DISTINCT CASE WHEN ge.type = "IssuesEvent" THEN ge.actor_id ELSE NULL END) AS issue_dev_num
    FROM github_events ge
    INNER JOIN coss_invest ci ON ci.github_name = ge.repo_name
    AND ci.company IS NOT NULL
    AND ci.github_name IS NOT NULL
    AND ci.has_github = TRUE
    AND ci.has_repo = TRUE
    AND ci.github_name = :repo_name
    AND ge.event_day != CURRENT_DATE()
    GROUP BY ci.github_name, ge.event_day
    """, nativeQuery = true)
    List<COSSDevDailyBean> getCOSSDevDailyBeanByRepoName(@Param("repo_name") String repoName);
}
