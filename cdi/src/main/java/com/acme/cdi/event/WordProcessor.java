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

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

public class WordProcessor {

    // @Any allows us to select a qualified event to fire
    @Inject
    @Any
    private Event<Document> documentEvent;

    private Document document;

    public void create(int pages) {
        if (document != null) {
            throw new IllegalStateException("Document open. Please close it first!");
        }
        document = new Document(pages);
    }

    public void close() {
        document = null;
    }

    @SuppressWarnings("serial")
    public void printUnknownSize(Document document) {
        documentEvent.select(new AnnotationLiteral<Default>() {
        }).fire(document);
    }

    public void print() {
        documentEvent.select(new PrintJobLiteral(getJobSize())).fire(document);
    }

    public JobSize getJobSize() {
        int pp = document.getPages();
        if (pp < 5) {
            return JobSize.SMALL;
        }
        if (pp < 50) {
            return JobSize.MEDIUM;
        }
        return JobSize.LARGE;
    }
}
