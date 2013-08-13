/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.cdi.event;

public class Packet {
    private int receiveCount = 0;
    private Object receiver = null;

    public void ack() {
        receiveCount++;
    }

    public void receivedBy(Object receiver) {
        this.receiver = receiver;
    }

    public boolean isReceived() {
        return receiveCount > 0;
    }

    public int getNumberTimesReceived() {
        return receiveCount;
    }

    public Object getReceiver() {
        return receiver;
    }
}
