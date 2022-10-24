# Copyright 2022 PingCAP, Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

.PHONY: build start restart stop

build:
	mvn clean package -DskipTests

start:
	pm2 start java -jar target/ossinsight-coss-0.0.1-SNAPSHOT.jar \
		--spring.config.location=${COSS_CONFIG}
		--name ossinsight-coss

restart:
	git pull origin main
	make build
	pm2 restart ossinsight-coss

stop:
	pm2 stop ossinsight-coss
	pm2 delete ossinsight-coss