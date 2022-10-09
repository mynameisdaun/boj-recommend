package com.daun.word.global;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

public class Id<R, V> {

    private final Class<R> reference;

    private final V value;

    private Id(Class<R> reference, V value) {
        this.reference = reference;
        this.value = value;
    }

    public static <R, V> Id<R, V> of(Class<R> reference, V value) {
        checkArgument(reference != null, "reference must be provided.");
        checkArgument(value != null, "value must be provided.");

        return new Id<>(reference, value);
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Id<?, ?> id = (Id<?, ?>) o;

        if (reference != null ? !reference.equals(id.reference) : id.reference != null) return false;
        return value != null ? value.equals(id.value) : id.value == null;
    }

    @Override
    public int hashCode() {
        int result = reference != null ? reference.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("reference", reference.getSimpleName())
                .append("value", value)
                .toString();
    }

}
