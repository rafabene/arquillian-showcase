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
package com.acme.cdi.decorator;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.beans10.BeansDescriptor;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acme.cdi.decorate.Announcer;
import com.acme.cdi.decorate.AnnouncerBean;
import com.acme.cdi.decorate.AnnouncerDecorator;

@RunWith(Arquillian.class)
public class AnnouncerDecoratorTestCase {
    @Deployment
    public static Archive<?> createArchive() {
        BeansDescriptor beansXml = Descriptors.create(BeansDescriptor.class);
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(Announcer.class.getPackage())
                .addAsManifestResource(
                        new StringAsset(beansXml.createDecorators().clazz(AnnouncerDecorator.class.getName()).up().exportAsString()),
                        beansXml.getDescriptorName());
    }

    @Inject
    AnnouncerBean bean;

    @Test
    public void shouldDecorateAnnouncement() {
        Assert.assertEquals("May I have your attention! School is out!", bean.makeAnnouncement("School is out!"));
    }
}
