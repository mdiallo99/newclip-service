package com.example.authenticationapp.proxyWS;

import org.glassfish.json.JsonPatchBuilderImpl;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ObjectHandler extends DefaultHandler {
    private JsonBuilderFactory jsonBuilderFactory;

    private JsonObjectBuilder builder;
    private String name;
    private Attributes attributes;
    private StringBuilder characters;

    private LinkedList<ObjectHandler> stack;
    private final List<ObjectHandler> children = new LinkedList<>();


    public ObjectHandler(final JsonBuilderFactory jsonBuilderFactory, final LinkedList<ObjectHandler> stack) {
        this.jsonBuilderFactory = jsonBuilderFactory;
        this.stack = stack;
    }

    public ObjectHandler() {
        this.jsonBuilderFactory = new JsonBuilderFactory() {
            @Override
            public JsonObjectBuilder createObjectBuilder() {
                return null;
            }

            @Override
            public JsonArrayBuilder createArrayBuilder() {
                return null;
            }

            @Override
            public Map<String, ?> getConfigInUse() {
                return null;
            }
        };
    }

    public LinkedList<ObjectHandler> getStack() {
        return stack;
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) {
        stack.getLast().onStartElement(qName, attributes);
    }

    @Override
    public void characters(final char[] ch, final int start, final int length) {
        stack.getLast().onCharacter(ch, start, length);
    }

    @Override
    public void endElement(final String uri, final String localName, final String qName) {
        stack.removeLast().onEndElement();
    }

    private void onStartElement(final String qName, final Attributes attributes) {
        if (this.name == null) {
            this.name = qName;
            this.attributes = attributes;
        } else {
            final ObjectHandler handler = new ObjectHandler();
            handler.name = qName;
            handler.attributes = attributes;
            handler.stack = stack;
            stack.add(handler);
            children.add(handler);
        }
    }

    private void onCharacter(final char[] ch, final int start, final int length) {
        if (characters == null) {
            characters = new StringBuilder();
        }
        characters.append(ch, start, length);
    }

    private void onEndElement() {
        builder = jsonBuilderFactory.createObjectBuilder();
        if (characters != null) {
            builder.add("__content__", characters.toString());
            characters = null;
        }
        if (attributes != null && attributes.getLength() > 0) {
            builder.add("__attributes__", IntStream.range(0, attributes.getLength())
                    .boxed()
                    .collect(
                            jsonBuilderFactory::createObjectBuilder,
                            (builder, idx) -> builder.add(attributes.getQName(idx), attributes.getValue(idx)),
                            JsonObjectBuilder::addAll)
                    .build());
        }
        children.forEach(c -> builder.add(c.name, c.builder));
        children.clear();
    }
}
