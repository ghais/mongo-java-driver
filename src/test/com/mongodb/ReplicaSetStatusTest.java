/**
 * Copyright (C) 2011 10gen Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb;

import com.mongodb.util.TestCase;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * This is a placeholder. A node needs to be able to be created outside of ReplicaSetStatus.
 */
public class ReplicaSetStatusTest extends TestCase {
    public ReplicaSetStatusTest() throws IOException, MongoException {
        cleanupMongo = new Mongo(new MongoURI("mongodb://127.0.0.1:27017,127.0.0.1:27018"));

        cleanupDB = "com_mongodb_unittest_ReplicaSetStatusUpdaterTest";
    }

    @Test
    public void testClose() throws InterruptedException {
        ReplicaSetStatus replicaSetStatus = new ReplicaSetStatus(cleanupMongo, cleanupMongo.getAllAddress());
        replicaSetStatus.start();
        assertNotNull(replicaSetStatus._replicaSetHolder.get());

        replicaSetStatus.close();

        replicaSetStatus._updater.join(5000);

        assertTrue(!replicaSetStatus._updater.isAlive());
    }
}

