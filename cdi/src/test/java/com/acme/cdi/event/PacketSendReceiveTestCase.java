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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class PacketSendReceiveTestCase {
    @Inject
    Instance<Packet> packetInstance;

    @Inject
    PacketSender sender;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(Packet.class, PacketSender.class, PacketReceiver.class, Tracer.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testReceivePacket() {
        Packet packet = packetInstance.get();
        assertFalse(packet.isReceived());
        assertNull(packet.getReceiver());
        sender.send(packet);
        assertTrue(packet.isReceived());

        assertEquals(1, packet.getNumberTimesReceived());
        assertNull(packet.getReceiver());
    }

    @Test
    public void testReceiveTracerPacket() {
        Packet packet = packetInstance.get();
        assertFalse(packet.isReceived());
        assertNull(packet.getReceiver());
        sender.sendTracer(packet);
        assertTrue(packet.isReceived());

        assertEquals(1, packet.getNumberTimesReceived());
        assertTrue(packet.getReceiver() instanceof PacketReceiver);
    }
}
