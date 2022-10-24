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

package com.pingcap.ossinsightcoss.interval;

import com.pingcap.ossinsightcoss.dao.COSSDevDailyRepository;
import com.pingcap.ossinsightcoss.dao.COSSInvestBean;
import com.pingcap.ossinsightcoss.dao.COSSInvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * DevDailyBuilder
 *
 * @author Icemap
 * @date 2022/10/24
 */
@Service
public class DevDailyBuilder {
    @Autowired
    COSSDevDailyRepository cossDevDailyRepository;

    @Autowired
    COSSInvestRepository cossInvestRepository;

    // refresh from bottom to top
    Stack<COSSInvestBean> refreshStack = new Stack<>();

    @Scheduled(fixedDelay=10, timeUnit=TimeUnit.SECONDS)
    public void buildAndRefreshDevDailyOfRepo() {
        if (refreshStack.isEmpty()) {
            refreshStack.addAll(cossInvestRepository.findAll());
            return;
        }

        cossDevDailyRepository.saveAll(
                cossDevDailyRepository.getCOSSDevDailyBeanByRepoName(
                        refreshStack.pop().getGithubName()
                )
        );
    }
}
