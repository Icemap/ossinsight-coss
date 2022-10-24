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

package com.pingcap.ossinsightcoss.controller;

import com.pingcap.ossinsightcoss.dao.COSSInvestBean;
import com.pingcap.ossinsightcoss.dao.COSSInvestRepository;
import com.pingcap.ossinsightcoss.util.ConvertUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ConfigController
 *
 * @author Icemap
 * @date 2022/10/20
 */
@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    ConvertUtil convertUtil;

    @GetMapping("/coss-invest")
    public Object getConfig() throws Exception {
        return convertUtil.readCOSSInvestBean();
    }
}
