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
package com.acme.cdi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class BeanManagerTestCase {
    @Deployment
    public static JavaArchive createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar") // archive name optional
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    BeanManager beanManager;

    @Test
    public void testCdiBootstrap() {
        assertNotNull(beanManager);
        assertFalse(beanManager.getBeans(BeanManager.class).isEmpty());
        printCdiImplementationInfo(beanManager);
    }

    protected void printCdiImplementationInfo(BeanManager beanManager) {
        String impl = beanManager.getClass().getPackage().getImplementationTitle();
        if (impl == null) {
            impl = beanManager.getClass().getPackage().getImplementationVendor();
        }
        if (impl != null) {
            System.out.println("CDI implementation: " + impl.replaceFirst("^([^ ]+)( .*)?$", "$1"));
        }
        else {
            System.out.println("Could not determine CDI implementation");
        }
        System.out.println("BeanManager implementation class: " + beanManager.getClass().getName());
    }
}
