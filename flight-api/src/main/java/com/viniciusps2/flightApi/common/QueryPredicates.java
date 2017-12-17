package com.viniciusps2.flightApi.common;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import java.util.Date;

public class QueryPredicates {
    private QueryPredicates () {}

    public static <T extends Enum<T>> Predicate isEqualWhenExists(EnumPath<T> path, T value) {
        return value != null ? path.eq(value) : null;
    }

    public static <T extends Number & Comparable<?>> Predicate isEqualWhenExists(NumberPath<T> path, T value) {
        return value != null ? path.eq(value) : null;
    }

    public static <T extends String> Predicate isEqualWhenExists(StringPath path, T value) {
        return value != null ? path.eq(value) : null;
    }

    public static Predicate matchDateRange(DateTimePath<Date> dateRangePath, DateRange dateRange) {
        if (dateRange == null) return null;
        Date from = dateRange.getFrom();
        Date until = dateRange.getUntil();
        return new BooleanBuilder()
                .and(from != null ? dateRangePath.goe(from) : null)
                .and(until!= null ? dateRangePath.loe(until) : null);
    }


}
