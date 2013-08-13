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
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class DocumentEventTestCase {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(Document.class, WordProcessor.class, PrintSpool.class, PrintJobLiteral.class, PrintJob.class, JobSize.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    WordProcessor processor;

    @Inject
    PrintSpool spool;

    @Test
    public void print_spool_should_observe_print_event() {
        processor.create(5);
        processor.print();
        assertEquals(1, spool.getNumDocumentsSent());
        assertNotNull(spool.getDocumentsProcessed());
        assertEquals(1, spool.getDocumentsProcessed().size());
        assertEquals(1, spool.getDocumentsProcessed(JobSize.MEDIUM).size());
        processor.close();

        processor.create(100);
        processor.print();
        assertEquals(2, spool.getNumDocumentsSent());
        assertNotNull(spool.getDocumentsProcessed());
        assertEquals(2, spool.getDocumentsProcessed().size());
        assertEquals(1, spool.getDocumentsProcessed(JobSize.LARGE).size());
        processor.close();

        processor.printUnknownSize(new Document(25));
        assertEquals(3, spool.getNumDocumentsSent());
        assertEquals(1, spool.getNumFailedDocuments());
    }
}
